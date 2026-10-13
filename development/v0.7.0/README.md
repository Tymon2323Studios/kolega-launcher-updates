# Kolega Client v0.7.0 — Development preview

**Not a released update.** This directory contains the main NEW implementation files for the upcoming v0.7.0 (Zoom, hotkeys, visuals). The complete source tree and Windows installer have been prepared for local testing. These files are **not** intended to be built by themselves; they are a development snapshot from a larger Fabric 1.21.10 + C# WPF project.

Changes: hold-to-zoom (default C, smooth 2–8×), keybind editor with collision prevention, Crosshair Accent, Clock / Speed / Sneak HUD, updated installation references to `kolega-client-0.7.0.jar`. Existing multiplayer and player profiles are not modified.

Required testing before public release: full Gradle/Fabric and WPF build on Windows, launch Minecraft 1.21.10, Zoom/mixin runtime, keybind persistence, offline-accepting multiplayer, rollback. **Do not create `site/release.json` until the signed binary and rollback have passed.**

Never commit private signing keys or access tokens.
