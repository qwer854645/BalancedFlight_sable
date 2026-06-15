package com.vice.balancedflight.content.flightAnchor;

import com.vice.balancedflight.content.flightAnchor.entity.FlightAnchorEntity;
import com.vice.balancedflight.foundation.compat.AscendedRingCurio;
import com.vice.balancedflight.foundation.compat.SableCompat;
import com.vice.balancedflight.foundation.config.BalancedFlightConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlightController
{
    public static void tick(Player player)
    {
        if (!canCreativeFly(player))
        {
            if (!player.isCreative() && player.getAbilities().mayfly)
            {
                stopFlying(player);
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200));
            }
            return;
        }

        if (!player.getAbilities().mayfly) {
            startFlying(player);
            if (player.hasEffect(MobEffects.SLOW_FALLING))
                player.removeEffect(MobEffects.SLOW_FALLING);
        }
    }

    public static void startFlying(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    public static void stopFlying(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
    }

    public static boolean canCreativeFly(Player player)
    {
        if (AscendedRingCurio.HasAscendedRing(player))
        {
            if (BalancedFlightConfig.CreativeAscended.get())
                return true;

            return BalancedFlightConfig.CreativeAnchor.get() && IsWithinFlightRange(player);
        }

        return BalancedFlightConfig.CreativeAnchor.get() && IsWithinFlightRange(player);
    }

    private static boolean IsWithinFlightRange(Player player)
    {
        if (!SableCompat.allowsAnchorFlightInLevel(player))
            return false;

        double anchorDistanceMultiplier = BalancedFlightConfig.anchorDistanceMultiplier.get();
        Level level = player.level();
        Vec3 playerPos = player.position();

        return FlightAnchorEntity.ActiveAnchors
                .values()
                .stream()
                .anyMatch(anchor -> {
                    double radius = anchorDistanceMultiplier * anchor.getSpeed();
                    return SableCompat.horizontalDistanceSqr(level, playerPos, anchor.getBlockPos()) < radius * radius;
                });
    }
}
