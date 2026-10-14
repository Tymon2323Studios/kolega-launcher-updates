# Kolega Client v0.8.0 — VISUALS OVERHAUL (Stage 1 / DEV)

**Do not deploy this folder or enable auto-updates from it.** This is a developer status snapshot and checklist. The full v0.8.0 115-file source ZIP is available separately in the TymonIndustrials project conversation for local Windows tests.

## Implemented in the full source ZIP (pending real Minecraft/Fabric build)

- Armor Status — four personal armor slots, current durability as percentages.
- Inventory HUD — 27 main inventory slots (9×3), read-only view.
- Potions HUD — current effects and remaining duration.
- Target HUD — pointed-at player name and actual health (preview only in HUD editor).
- Notifications — bounded, timed ON/OFF module notifications.
- Watermark 2.0 — Kolega Client, local nickname, FPS, CPS. TPS is deliberately not invented.
- Existing v0.7.1 Zoom, keybinds, themes, multiplayer and HUD Editor retained.

The standalone `NotificationFeed.java` is checked in here as a small testable component; this repo is an **update channel**, not the canonical complete source repository.

## Next stages BEFORE ClickGUI overhaul

**3D / render-dependent visuals:** Shaded Hands (glow/snow/chroma/glass), Jump Circles (world-ground shapes), Target Render (Legacy/Ghost/Circle/Crystal), China Hat, Custom Hitbox, Fog, Full Bright, Hit Particles, No Fire, Hand Tweaks, Kill Effect, Trinket, Totem Pop.

**Custom HUD / utilities:** custom Hotbar, multiple Crosshair styles, Fake Player local training dummy, Hit Sounds, Name Protect. Name Protect is a local *display alias*, not network identity masking, IP protection or anti-DDoS.

## Non-negotiable release checklist

- [ ] Full Gradle/Fabric 1.21.10 + .NET 10 Windows build.
- [ ] Test modules in survival, multiplayer, spectator and HUD editor.
- [ ] Test new modules' configuration persistence / controls.
- [ ] Test the old v0.7.1 client upgrade and rollback.
- [ ] Signed manifest RSA-PSS, ZIP hash SHA256, no signing PEM in public repository.
- [ ] First v0.8.0 signed release must contain *both* ClientFiles/kolega-client-0.8.0.jar and legacy ClientFiles/kolega-client-0.7.1.jar for the installed v0.7.1 updater.

Only after gameplay tests should a verified release.json and update ZIP be committed to `main/site/`. This development branch does not deploy Pages.
