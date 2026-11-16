package com.vice.balancedflight.foundation.compat;

import com.vice.balancedflight.BalancedFlight;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;

public class AscendedRingCurio
{
    public static boolean HasAscendedRing(LivingEntity entity) {
        if (!(entity instanceof Player player)) {
            return false;
        }
        return CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.findCurios(BalancedFlight.ASCENDED_FLIGHT_RING.get()).stream()
                        .anyMatch(result -> !result.slotContext().cosmetic()
                                && handler.isSlotActive(result.slotContext().identifier(), result.slotContext().index())))
                .orElse(false);
    }

    public static boolean canEquipRing(SlotContext target) {
        if (target.cosmetic())
            return true;

        return CuriosApi.getCuriosInventory(target.entity())
                .map(handler -> handler.findCurios(BalancedFlight.ASCENDED_FLIGHT_RING.get()).stream()
                        .filter(result -> !result.slotContext().cosmetic())
                        .allMatch(result -> result.slotContext().identifier().equals(target.identifier())
                                && result.slotContext().index() == target.index()))
                .orElse(false);
    }
}
