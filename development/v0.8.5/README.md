# Kolega Client v0.8.5 — PvP & HUD Expansion (DEV)
**TymonIndustrials · Minecraft Java 1.21.10 · Fabric API · Java 21 / WPF .NET 10**

This is a **developer-only changelog** and test checklist. The **complete source tree** is provided separately as `KolegaLauncher-v0.8.5-PVP-HUD-DEV-SOURCE.zip` in the project conversation. This update-channel repository is not the source distribution.

## Five new modules
- **Custom Hotbar** — translucent rounded frame around the vanilla hotbar and frame-time-animated selected-slot accent, 4 styles (Neon/Glass/Minimal/Rose), opacity/radius/speed. Vanilla item rendering/cooldowns remain intact. This does *not* replace every vanilla sprite.
- **Totem Pop** — intercept client packet `ClientPacketListener.handleEntityEvent`, event **35**. Show local animation/popup with bounded particles, styles, range/player filter and duration; preserve vanilla totem effect.
- **Cooldown Timers** — actual vanilla ender-pearl cooldown **percentage**, recent totem activation age, optional *local and approximate* golden-apple timer after observed use. No claim that vanilla golden apple has a cooldown.
- **Kill Effect** — bounded local particles after the *observed* death of a recently hit target. This is not authoritative proof of a server-awarded kill.
- **Trinket** — geometric KC logo above inventory screen, configurable size, vertical offset and four color modes.

Modules default **OFF**, and their `VisualSettings` sliders/modes use the existing ClickGUI inspector and `ConfigManager` JSON settings migration. `CooldownTimersModule` uses the existing HUD Editor for drag/scale/grid. All previous v0.8.4 features were preserved in the source tree.

## Verification already performed
- Java 21 unit tests: all **21** visual settings definitions and new settings bounds/serialization state.
- Java 21 headless API-stub compilation: **21 module classes**, Hotbar drawing bridge, Totem/Trinket Mixins, PvP events.
- `javap` bytecode modifier test: static NoFire callback; instance NoWeather/Totem/Trinket callbacks.
- Existing ClickGUI, Crosshair, HUD, Zoom and Hit Sounds regression checks.
- Static version/JAR path/XAML/release-gate checks.

**Not performed:** full Gradle/Fabric build against the actual mapped game, Windows WPF build and real gameplay tests. Those are required before release.

## Production safety
Published `main/site/release.json` stays at **v0.8.0**. `OPUBLIKUJ-UPDATE.bat` in the DEV ZIP intentionally exits with error rather than publishing. Never upload a private PEM or access token to this repository.

See [TEST-PLAN.md](TEST-PLAN.md).
