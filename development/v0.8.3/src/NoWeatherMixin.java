package pl.kolegaclient.mixin;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.kolegaclient.KolegaClient;

/** Skips only local weather frame pass, does not modify server weather or physics. */
@Mixin(LevelRenderer.class)
public abstract class NoWeatherMixin {
    @Inject(method="addWeatherPass",at=@At("HEAD"),cancellable=true,require=0)
    private void kolega$noWeather(CallbackInfo ci){
        var m=KolegaClient.MODULES.find("noweather");
        if(m!=null && m.enabled())ci.cancel();
    }
}
