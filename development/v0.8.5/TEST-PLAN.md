# Kolega Client v0.8.5 — manual release gate

- [x] Pure Java 21 settings tests: 21 module definitions, 5 new controls, limits, modes, migration
- [x] New module + Mixin stub compilation; injection handler modifier inspection via javap
- [x] Prior ClickGUI, Crosshair, HUD Editor and Hit Sounds headless regression tests
- [ ] **On Windows**: `narzedzia/testy/sprawdz-v085-windows.bat` passes
- [ ] Full **Minecraft 1.21.10** + Fabric Gradle compile and **.NET 10** launcher build
- [ ] Client boots without startup Mixin errors
- [ ] Custom Hotbar: all 9 slots, offhand, cooldowns, enchanted items, switching quickly, ESC menus
- [ ] Totem Pop: true server entity event #35 (self/other), particle limit and popup duration
- [ ] Cooldown Timers: pearl cooldown percentage and local timers clearly distinguished
- [ ] Kill Effect: dying target after last hit, no particles on unrelated deaths, reset after world change
- [ ] Trinket: inventory UI renders without blocking slot clicks, hover descriptions, custom resize
- [ ] Full ClickGUI inspector edits each setting and preserves them after a game restart
- [ ] Cooldown HUD drag, snap and 50%-200% scale in HUD Editor
- [ ] All v0.8.4 modules, profiles, Zoom, Crosshair and multiplayer still work
- [ ] Backup, signed updater, rollback and staged multiplayer client tests

**STOP:** Do not publish signed `release.json` or ZIP to production before all required Windows/game tests. Main stays v0.8.0.
