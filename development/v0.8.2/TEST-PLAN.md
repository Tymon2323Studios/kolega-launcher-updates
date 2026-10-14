# Kolega Client v0.8.2 — release gate

- [ ] Java 21, Fabric 1.21.10: full Gradle build of `kolega-client-0.8.2.jar`.
- [ ] .NET 10 Windows WPF launcher / standalone updater builds and starts.
- [ ] Backup v0.8.0 and `config/kolega-client.json` before testing.
- [ ] ClickGUI Right Shift: no crashes on 854×480 / 1280×720 / 1920×1080.
- [ ] Rounded corners, theme opacity and KC header/Watermark are legible.
- [ ] Crosshair gear opens Studio; preview appears over actual game world and matches gameplay draw.
- [ ] Six shapes, three color modes, RGB sliders, gap/size/outline and opacity apply.
- [ ] Crosshair settings persist through Minecraft restart.
- [ ] HUD Editor: scaling 50/100/200% transforms real text/icons as well as boxes.
- [ ] Dragging/resizing/scroll bounds align with visible scaled widgets.
- [ ] G toggles grid; N toggles snap; PPM and Reset All work as documented.
- [ ] Scales, grid and snap persist and existing v0.8.0 HUD positions load correctly.
- [ ] Smooth Zoom, existing keybinds, saved profiles and multiplayer unchanged.
- [ ] Signed v0.8.0 → v0.8.2 upgrade on a disposable copy, failed-update rollback, no loss of settings.

Only after completing the list, sign/review/publish `release.json` and ZIP to `main/site/`. Do not put private PEM into the repo.