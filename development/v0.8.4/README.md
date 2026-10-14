# Kolega Client v0.8.4 — Rendering & World (DEV)

TymonIndustrials — Minecraft Java **1.21.10** + Fabric API, Java 21, Windows .NET 10.

**Developer-only**: complete source project is supplied as `KolegaLauncher-v0.8.4-RENDERING-WORLD-DEV-SOURCE.zip` in the project conversation, not as a signed public release. This branch contains release notes / reference implementation fragments. The full source is NOT reproduced in this GitHub update-channel repo.

## Six new local visual modules

| Module | Current implementation | Persistent ClickGUI settings |
|---|---|---|
| China Hat | Conical 3D particle cap following local player; third-person only, not a GPU mesh | radius, height, detail, particle style |
| Custom Sky | Tint color of the client's sky (no custom textures/shaders) | RGB, preset, strength |
| Time Changer | Override client-only `ClientLevelData.getDayTime()` return (server time untouched) | hour 0–23, dawn/noon/sunset/night/midnight |
| Custom Hitbox | Local 3D particle outline around the current targeted living entity; no through-wall reveal | range, detail, particle style |
| Custom Fog | Fog tint and draw-distance scaling in renderer GPU buffer | preset, RGB, color mix %, distance 30–200% |
| No Hurt Cam | Cancel camera-only hurt bob, optional first-person-only | scope |

Existing ten v0.8.3 visuals, ClickGUI themes, crosshair, HUD scaling, profiles, hotkeys, Zoom and Minecraft multiplayer are retained. Every new module defaults to OFF, settings are saved in the existing `visualSettings` block of `kolega-client.json` with clamps.

## Technical & compatibility notes

This is deliberately not described as a true volumetric fog renderer, shaded hat geometry or resource-pack-based sky textures. Particle effects remain client-only, and no server packets/physics are modified. Settings migration from previous versions preserves older `visualSettings` keys. The new mixins target Mojang 1.21.10 mappings and require real Fabric runtime verification before any release.

### Testing done
- Java 21 tests: VisualSettings (16 definitions), WorldAppearance colors/time/fog.
- API-stub compilation: 16 module classes + world effects + 6 mixins, and `javap` checks of critical callback modifiers.
- Existing GUI / HUD / Crosshair / Hit Sounds regressions; version and ZIP safety audits.

### Still required
- Full Gradle/Fabric and WPF builds on Windows.
- Minecraft starts (no mixin crash); gameplay, multiplayer, each effect ON/OFF, FPS, config restart.
- Signed updater from older versions and safe rollback.

**Production channel is intentionally unchanged (v0.8.0).** `OPUBLIKUJ-UPDATE.bat` in the full DEV archive is **disabled**, so this work cannot accidentally become a public release. Never commit private RSA signing keys.

[See test plan](TEST-PLAN.md).