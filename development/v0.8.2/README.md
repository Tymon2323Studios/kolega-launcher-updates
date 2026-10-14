# Kolega Client v0.8.2 — ClickGUI Polish (Developer Preview)

**TymonIndustrials** · Minecraft Java 1.21.10 · Fabric · Java 21 · .NET 10 WPF

This branch contains development release notes and acceptance requirements. **The complete 141-file v0.8.2 source ZIP is provided separately in the ChatGPT conversation**; this public repository is the signed update channel, not yet the complete standalone source archive.

## Implemented in the full source ZIP
- Anti-aliased rounded corners for ClickGUI and HUD cards, refined outlines/toggles.
- Balanced geometric **KC monogram** in the ClickGUI header and Watermark.
- **Custom Crosshair** gear button next to the module toggle.
- **Crosshair Studio** with in-game-world live preview: PLUS / DOT / CIRCLE / X / DIAMOND / DYNAMIC; theme/custom RGB/rainbow, size, gap, thickness, opacity, outline and center dot.
- **HUD Editor**: 50–200% real widget rendering scale using Minecraft's 2D matrix stack, resize handle and mouse wheel; toggle grid **G**, snap-to-grid **N**, save all settings in JSON.
- Existing Zoom, keybinds, modules, HUD positions, favorites, profiles and multiplayer left compatible.

## Validation performed
- Java 21 headless API-stub compile and GUI smoke tests (multiple viewports, UI clicks, arrows, rendering).
- Pure model regression tests for crosshair options, HUD scaling, save-compatible defaults, transparency, theme and hotkeys.
- Static version and source structure checks; archive integrity.
- **Not yet tested:** full real Gradle/Fabric build, WPF compilation and multiplayer runtime on Windows.

## Safe release policy
Production `main/site/release.json` remains **v0.8.0**. Do not publish a v0.8.2 signed update before real-world test plus rollback verification; private signing keys never belong in the repository.

See [TEST-PLAN.md](TEST-PLAN.md).