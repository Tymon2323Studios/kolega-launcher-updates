# Kolega Client v0.8.1 — CLICKGUI OVERHAUL 💜

**Development preview — not published as an automatic update.** The full 129+ file source tree is being tested in a standalone Windows source ZIP delivered in our TymonIndustrials development conversation. This repo is the **public signed update channel**, not the primary canonical source tree.

## What's implemented in the local full source package

- 12 GUI palettes: Neon Purple, Dark Glass, Midnight Blue, Cherry Red, Emerald, Ice Frost, Sunset, Rose Pink, Pure Black, Light Clean, Galaxy, Golden.
- Appearance: RGBA-style opacity slider (45–100%), rounded corners (0–22px), custom RGB accent, glow, shadows, gradient, animations toggle/speed (50–200%), density presets.
- Right Shift ClickGUI: responsive sidebar, separate module inspector on large displays, narrow-layout detail view, scrollable module cards, per-category searching, Favorites, editable hotkeys with collision protection.
- 3 customizable theme slots persisted in `config/kolega-client.json` under new `clickGui` / `clickGuiPresets` fields. Legacy theme, accent, HUD positions and 3 HUD profiles are preserved.
- Smooth Zoom and existing v0.8.0 visual modules preserved. Multiplayer implementation unchanged.
- No real blur shader: semi-transparent compositing avoids costly shader/mixin dependency; exact glass appearance depends on background.

## Checks complete
- Java 21: GUI + real ConfigManager compile with signature-only stand-ins for Minecraft/Gson APIs.
- 12 style-theme checks, bounds, opacity, RGB, favorites, transition easing.
- Headless smoke tests for 6 screen sizes, module navigation, scrolling, editable search and Backspace.
- Legacy Zoom, Keybind, HUD layout and Notification tests.
- Version and installer source/XAML consistency checks.

## Tests still mandatory BEFORE publishing to site/
- [ ] Actual Fabric 1.21.10 Gradle build with installed dependencies
- [ ] Actual Windows .NET 10 build
- [ ] Right Shift ClickGUI in live Minecraft
- [ ] Colors, opacity, rounded corners, input interactions on different GUI scales
- [ ] Persist config and profiles through restart
- [ ] FPS/load and regressions (Zoom, HUD, servers)
- [ ] Signed update from v0.8.0 to v0.8.1 + rollback on disposable installation

**Do NOT create a new main/site/release.json or ZIP until all real-world tests pass.** Current public stable release is v0.8.0; github-pages workflow triggers only on main.
