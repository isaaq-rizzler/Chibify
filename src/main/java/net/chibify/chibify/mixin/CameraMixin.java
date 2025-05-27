package net.chibify.chibify.mixin;

import net.chibify.chibify.config.ModConfig;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow private float cameraY;

    @Inject(method = "updateEyeHeight", at = @At("TAIL"))
    private void lowerCameraY(CallbackInfo ci) {
        if (!ModConfig.INSTANCE.shrinkSelf || !ModConfig.INSTANCE.AccurateEyeHeight) return;

        this.cameraY *= 0.78f;
    }
}