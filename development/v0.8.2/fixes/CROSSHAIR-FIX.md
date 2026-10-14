# v0.8.2 Crosshair Center Fix — developer patch

**Problem:** custom crosshair was drawn as a generic end-of-HUD overlay while vanilla Minecraft CROSSHAIR continued drawing. Two reticles overlapped and appeared offset.

**Fix in complete source ZIP shared in ChatGPT:** use `HudElementRegistry.replaceElement(VanillaHudElements.CROSSHAIR, vanilla -> (graphics, tickCounter) -> { ... })` on the original vanilla layer. The handler delegates to the original vanilla rendering when custom crosshair is disabled, when a GUI is open, HUD is hidden, spectator mode or third-person view. The custom crosshair is no longer rendered through ModuleManager's generic `onHud` loop. The painter aligns DOT and PLUS geometry on odd and even GUI-scaled viewports using subpixel translations.

Headless Java 21 tests cover six viewport sizes; default/custom switching, settings, GUI, spectator, third-person and hidden HUD. **Real Fabric 1.21.10 gameplay verification still required**.

This development branch is not a published or signed update. Public `main/site/release.json` is **v0.8.0** and unchanged. Full updated source ZIP is provided separately in the project conversation; repository contains developer change summary and bridge source only.

Fabric references: `HudElementRegistry.replaceElement`, `VanillaHudElements.CROSSHAIR` (Fabric HUD API for Minecraft 1.21.10).