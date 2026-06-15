package com.vice.balancedflight.foundation.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class SableCompat
{
    private static final boolean LOADED = ExternalMods.SABLE.isLoaded();
    private static final Object COMPANION = resolveCompanion();
    private static final Method PROJECT_OUT_OF_SUB_LEVEL = resolveMethod("projectOutOfSubLevel", Level.class, net.minecraft.core.Position.class);
    private static final Method IS_IN_PLOT_GRID = resolveMethod("isInPlotGrid", Level.class, int.class, int.class);

    private SableCompat() {}

    public static boolean isLoaded()
    {
        return LOADED && COMPANION != null;
    }

    public static boolean allowsAnchorFlightInLevel(Player player)
    {
        if (!isLoaded())
            return player.level().dimension() == Level.OVERWORLD;

        Level level = player.level();
        return level.dimension() == Level.OVERWORLD
                || isInPlotGrid(level, player.blockPosition());
    }

    public static double horizontalDistanceSqr(Level level, Vec3 from, BlockPos to)
    {
        Vec3 target = Vec3.atCenterOf(to);

        if (!isLoaded())
            return horizontalDistanceSqr(from, target);

        Vec3 globalFrom = projectOutOfSubLevel(level, from);
        Vec3 globalTo = projectOutOfSubLevel(level, target);

        return horizontalDistanceSqr(globalFrom, globalTo);
    }

    private static Vec3 projectOutOfSubLevel(Level level, Vec3 position)
    {
        if (PROJECT_OUT_OF_SUB_LEVEL == null)
            return position;

        try {
            Object result = PROJECT_OUT_OF_SUB_LEVEL.invoke(COMPANION, level, position);
            return result instanceof Vec3 vec3 ? vec3 : position;
        } catch (ReflectiveOperationException exception) {
            BalancedFlightCompat.LOGGER.warn("Failed to project position for Sable compatibility", exception);
            return position;
        }
    }

    private static boolean isInPlotGrid(Level level, BlockPos pos)
    {
        if (IS_IN_PLOT_GRID == null)
            return false;

        try {
            Object result = IS_IN_PLOT_GRID.invoke(COMPANION, level, pos.getX(), pos.getZ());
            return result instanceof Boolean bool && bool;
        } catch (ReflectiveOperationException exception) {
            BalancedFlightCompat.LOGGER.warn("Failed to query Sable plot grid", exception);
            return false;
        }
    }

    private static double horizontalDistanceSqr(Vec3 from, Vec3 to)
    {
        double dx = from.x - to.x;
        double dz = from.z - to.z;
        return dx * dx + dz * dz;
    }

    private static Object resolveCompanion()
    {
        if (!LOADED)
            return null;

        try {
            Class<?> companionClass = Class.forName("dev.ryanhcode.sable.companion.SableCompanion");
            Field instanceField = companionClass.getField("INSTANCE");
            return instanceField.get(null);
        } catch (ReflectiveOperationException exception) {
            BalancedFlightCompat.LOGGER.warn("Sable is loaded but Sable Companion API is unavailable", exception);
            return null;
        }
    }

    private static Method resolveMethod(String name, Class<?>... parameterTypes)
    {
        if (COMPANION == null)
            return null;

        try {
            return COMPANION.getClass().getMethod(name, parameterTypes);
        } catch (ReflectiveOperationException exception) {
            BalancedFlightCompat.LOGGER.warn("Missing Sable Companion method: {}", name, exception);
            return null;
        }
    }
}
