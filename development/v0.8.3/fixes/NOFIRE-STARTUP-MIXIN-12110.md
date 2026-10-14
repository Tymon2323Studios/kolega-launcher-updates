# v0.8.3 DEV — No Fire startup Mixin crash fix

## Diagnosis from real logs (2026-10-14 19:08:31)

Minecraft/Fabric 1.21.10 failed during game initialization with:
```
InvalidInjectionException: non-static callback method ... kolega$noFire targets a static method which is not supported
```

**Root cause:** target `net.minecraft.client.renderer.ScreenEffectRenderer.renderFire` is **static** in Minecraft 1.21.10. Our `NoFireMixin.kolega$noFire(CallbackInfo)` callback was accidentally **non-static**. This breaks class transformation at startup even while No Fire module is OFF. The preceding unrelated Mojang profile-properties 401 is not the cause.

**Minimal source fix:**
```java
@Inject(method="renderFire", at=@At("HEAD"), cancellable=true, require=0)
private static void kolega$noFire(CallbackInfo ci) {
    var module=KolegaClient.MODULES.find("nofire");
    if(module!=null&&module.enabled()) ci.cancel();
}
```

`NoWeatherMixin` stays a non-static callback, because `LevelRenderer.addWeatherPass` is an instance method in 1.21.10.

## Regression prevention
The complete source ZIP (`KolegaLauncher-v0.8.3-NOFIRE-STARTUP-FIX-SOURCE.zip`, provided separately in ChatGPT) updates `tools/test-v083-modules.py` to inspect **compiled bytecode via javap** and assert:
- No Fire callback is STATIC
- No Weather callback is non-static

The Windows `narzedzia/testy/sprawdz-v083-windows.bat` now invokes that test. Deliberately reverting No Fire to non-static was tested to fail, while corrected sources passed. Also passed Java 21 headless module/API/GUI tests, Hit Sounds regression and structural checks.

**Not performed:** running full Fabric Gradle and Minecraft client in this environment. Requires Windows runtime verification.

## Release policy

This is development-only. **Do not publish to main/site/release.json** or share as production update before full Fabric build and real in-game smoke test. The public release remains v0.8.0. Private signing PEM never belongs in GitHub.

For safe retry: backup working installation and config, extract the full fixed ZIP in its own folder, run preflight, install test build, launch Minecraft, check No Fire ON/OFF and No Weather ON/OFF, then test other visuals.
