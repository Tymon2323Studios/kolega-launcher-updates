# Kolega Client 0.8.6.1 — Visual Repairs & Style Polish

**Developer source package prepared; NOT a published auto-update.** Full 227-file source archive was provided privately in the project conversation as `KolegaLauncher-v0.8.6.1-VISUAL-REPAIRS-SOURCE.zip`. No private signing PEM is included. This GitHub update-channel repository contains only release notes, not the full source archive.

Based on user screenshots:
- **Target Render:** Twin rotating, translucent pink/neon 3D spirals/ribbons around aimed entity, RGB/rainbow, radius, speed, opacity.
- **Jump Circles:** Fine luminous rings on the ground at jump takeoff; easing, expansion, fade, radius/duration/opacity.
- **China Hat:** True geometric translucent conical canopy with rim; fill/outline/glow, height/radius/opacity/color. Third-person only.
- **Custom Hitbox:** Real 3D box outline around visible living entity, aiming grace region and throttled entity scans.
- **Custom Hotbar:** Noticeable outlines and animated selected slot rendered *over* the vanilla bar while items/cooldowns remain intact.
- **Use Animations:** More pronounced, hand-specific PoseStack transforms while using an item.
- **Shaded Hands:** Glow full-bright model lighting and diffused HUD accents. **Chroma/Snow/Glass are NOT true material shaders yet** and should not be represented as completed shader rendering.
- Launcher mod version advanced to **0.8.6.1**, with no DEV badge in UI. All 25 settings definitions retained and persisted to JSON.

### Tests completed
Java 21 pure math/settings unit tests; Java 21 mock-API compile for geometry, selector, 21 existing modules, four FPP modules, mixins; older crosshair/HUD regressions; signed release/legacy source inspections; ZIP integrity. **All of these are preflight tests, not a real Gradle/Fabric build or rendering test.**

### Remaining release gate
Full Gradle compile using Minecraft/Fabric 1.21.10 and WPF .NET10 build on Windows; actual startup; visual comparison with provided references; color/transparency/depth, FPS, settings persistence; legacy updater compatibility/rollback.

Public `main/site/release.json` stays on **0.8.0**. `OPUBLIKUJ-UPDATE.bat` and the direct PowerShell publish entrypoint in this source package are explicitly disabled.

**Do not publish before real game testing.**