# Kolega Client v0.7.1 — Smooth Zoom Hotfix (preview)

Patch over tested v0.7.0: render-frame-based, critically damped FOV easing replaces 20 TPS-only animation. Zoom key input is now polled per rendered frame rather than once per game tick. Hold C, configurable zoom strength (2–8x), HOTKEYS and saved configuration remain unchanged. Time-based interpolation provides approximately 200–300ms soft in/out at different FPS and handles mid-flight reversals and clock gaps.

Patch files:
- `src/ZoomSmoother.java`: stand-alone, testable spring integrator.
- `src/HotkeyManager.java`: per-frame zoom sampling (do NOT toggle zoom on ticks).
- `tests/ZoomSmootherTest.java`: headless checks at 30/60/144/240 FPS, bounce and pause.

Full Windows-ready **source** ZIP is provided separately in the ChatGPT conversation. The repository contains only these patch files and is NOT a complete standalone build. Requires tested source tree for Fabric Minecraft 1.21.10 / Java 21 and .NET 10 launcher. Neither the signed manifest nor an automatic release is published. Test on Windows first; leave v0.7.0 working until validated.

To integrate into full source: copy `src/` classes to `client-mod-source/src/main/java/pl/kolegaclient/input/`, bump mod/launcher references from 0.7.0 to 0.7.1, build JAR+EXE with official dependencies. Do not upload any private signing key.
