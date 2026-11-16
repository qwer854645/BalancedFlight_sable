package com.vice.balancedflight.content.flightAnchor;

import com.vice.balancedflight.content.flightAnchor.entity.FlightAnchorEntity;
import com.vice.balancedflight.BalancedFlight;
import com.vice.balancedflight.foundation.compat.AscendedRingCurio;
import com.vice.balancedflight.foundation.compat.SableCompat;
import com.vice.balancedflight.foundation.config.BalancedFlightConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlightController
{
    private static final ResourceLocation FLIGHT_MODIFIER = ResourceLocation.fromNamespaceAndPath(
            BalancedFlight.MODID, "creative_flight");

    public static void tick(Player player)
    {
        if (player.level().isClientSide)
            return;

        if (!canCreativeFly(player))
        {
            boolean wasFlying = player.getAbilities().flying;
            if (stopFlying(player) && FlightSafety.needsSlowFalling(wasFlying, player.onGround(),
                    player.isFallFlying(), player.isAlive(), player.hasEffect(MobEffects.SLOW_FALLING)))
            {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200));
            }
            return;
        }

        startFlying(player);
    }

    public static void startFlying(Player player) {
        if (player.level().isClientSide)
            return;

        var attribute = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (attribute != null && !attribute.hasModifier(FLIGHT_MODIFIER)) {
            attribute.addTransientModifier(new AttributeModifier(FLIGHT_MODIFIER, 1,
                    AttributeModifier.Operation.ADD_VALUE));
        }
    }

    public static boolean stopFlying(Player player) {
        if (player.level().isClientSide)
            return false;

        var attribute = player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT);
        if (attribute == null || !attribute.hasModifier(FLIGHT_MODIFIER))
            return false;

        boolean couldFly = player.mayFly();
        attribute.removeModifier(FLIGHT_MODIFIER);
        if (couldFly && !player.mayFly()) {
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
            return true;
        }
        return false;
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
                .filter(anchor -> anchor.getLevel() == level && !anchor.isRemoved())
                .anyMatch(anchor -> {
                    double radius = anchorDistanceMultiplier * anchor.getSpeed();
                    return SableCompat.horizontalDistanceSqr(level, playerPos, anchor.getBlockPos()) < radius * radius;
                });
    }
}
