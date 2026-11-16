package com.vice.balancedflight.foundation.events;

import com.vice.balancedflight.content.flightAnchor.FlightController;
import com.vice.balancedflight.content.flightAnchor.entity.FlightAnchorEntity;
import com.vice.balancedflight.foundation.compat.AscendedRingCurio;
import com.vice.balancedflight.foundation.config.BalancedFlightConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

@EventBusSubscriber
public class CommonEvents
{
    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        if (!event.getLevel().isClientSide())
            FlightAnchorEntity.ActiveAnchors.values().removeIf(anchor -> anchor.getLevel() == event.getLevel());
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event)
    {
        Player player = event.getEntity();
        FlightController.tick(player);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (event.getSource().is(DamageTypes.FALL)) {
            if (event.getEntity() instanceof Player player) {
                if (AscendedRingCurio.HasAscendedRing(player) && BalancedFlightConfig.disableFallDamageWhenWearingRing.get())
                    event.setCanceled(true);

                if (BalancedFlightConfig.disableFallDamageNearAnchor.get() && FlightController.isWithinFlightRange(player))
                    event.setCanceled(true);
            }
        }
    }
}
