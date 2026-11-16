package com.vice.balancedflight.foundation.mixin;

import com.vice.balancedflight.content.flightAnchor.FlightController;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerFlightMixin {
    @Inject(method = "tryToStartFallFlying", at = @At("HEAD"), cancellable = true)
    private void balancedflight$preferRingFlight(CallbackInfoReturnable<Boolean> cir) {
        if (FlightController.shouldPreventElytraTakeoff((Player) (Object) this))
            cir.setReturnValue(false);
    }
}
