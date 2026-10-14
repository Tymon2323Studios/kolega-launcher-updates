# v0.8.4 Windows / Fabric 1.21.10 release gate

- [x] Java 21 / API stub compilation, new settings, appearance tests, previous regressions
- [ ] Run `narzedzia/testy/sprawdz-v084-windows.bat` on Windows
- [ ] Full Fabric Gradle build, .NET 10 build and install to disposable backup of existing launcher
- [ ] Fabric loads all 6 mixins without InvalidInjectionException
- [ ] China Hat: third-person F5, movement, crouch, animation, 3 particle modes, no huge FPS regression
- [ ] Custom Sky: presets, RGB, on/off in overworld and other dimensions
- [ ] Time Changer: preset clock, night/day, return to current server time on disabling
- [ ] Custom Hitbox: nearby targeted living entity only, corner correctness, no targets when looking at blocks
- [ ] Custom Fog: distance/color in rain, caves, underwater and with existing shaders if used
- [ ] No Hurt Cam: actual damage event, HP/knockback unchanged, mode ALL/FPP
- [ ] ClickGUI sliders persisted to `kolega-client.json`, all 16 module options, restart game
- [ ] Zoom, Crosshair, HUD editor, older settings and multiplayer remain functional
- [ ] Signed updater/rollback with legacy clients; keep source DEV until separately approved

Do **not** publish a v0.8.4 manifest or installer before these checks. Production v0.8.0 remains live.