package pl.kolegaclient.mixin;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.kolegaclient.KolegaClient;

/** Client-only visual hook; does not disable fire damage. Requires real 1.21.10 test. */
@Mixin(ScreenEffectRenderer.class)
public abstract class NoFireMixin {
    @Inject(method="renderFire",at=@At("HEAD"),cancellable=true,require=0)
    private void kolega$noFire(CallbackInfo ci){
        var m=KolegaClient.MODULES.find("nofire");
        if(m!=null && m.enabled())ci.cancel();
    }
}
