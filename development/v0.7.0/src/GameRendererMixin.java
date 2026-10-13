package pl.kolegaclient.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.kolegaclient.KolegaClient;

/** View-only zoom: never modifies the saved Minecraft FOV option. */
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void kolega$zoom(Camera camera, float partialTick, boolean changingFov,
                              CallbackInfoReturnable<Float> cir) {
        float factor = KolegaClient.HOTKEYS.smoothFactor();
        if (factor > 1.001f && changingFov) {
            cir.setReturnValue(Math.max(7.0f, cir.getReturnValue() / factor));
        }
    }
}
