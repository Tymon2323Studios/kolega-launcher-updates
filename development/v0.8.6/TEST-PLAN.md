# Kolega Client 0.8.6 — release gate

- [x] 25 visual settings definitions, 4 new modules, saved settings with migration/clamps.
- [x] FirstPersonHandsMixin source checked against Minecraft 1.21.10 `ItemInHandRenderer.renderArmWithItem` Mojang mapping, Java 21 stub compile and javap check.
- [x] GUI 6-size smoke test, Hit Sounds, Crosshair, HUD and earlier module regressions.
- [x] Launcher XAML static v0.8.3 labels fixed to compiled EXE version; Fabric ClickGUI header uses mod metadata.
- [x] Source-only publishing gate; disabled BAT and direct PowerShell publisher.
- [ ] Run `narzedzia/testy/sprawdz-v086-windows.bat` on Windows.
- [ ] Full Gradle/Fabric 1.21.10 build and .NET 10 launcher build on Windows.
- [ ] Minecraft start — verify `FirstPersonHandsMixin` does not cause startup crashes.
- [ ] Hand Tweaks: left/right hand, Custom/Classic/Compact/Low/Center, all rotations and reset.
- [ ] Swing Animations: Vanilla/Classic/Smooth/Slide/Spin with sword/bow/shield; no gameplay changes.
- [ ] Use Animations: vanilla/bounce/float/lower/sway, food/potions/other items.
- [ ] Shaded Hands glow and four accent modes across fullscreen/windowed, HUD/no HUD.
- [ ] ClickGUI values persist after restart; no FPS / render leaks.
- [ ] Screens in WPF show 0.8.6 (sidebar, title, About, Updates), not 0.8.3.
- [ ] Signed ZIP update and rollback tested from v0.7.0/v0.7.1/v0.8.0, GitHub 100 MB limit respected.
- [ ] Separate human release approval for `main/site/release.json` and distribution ZIP.

No private signing PEM or build artifacts should be committed to GitHub.
