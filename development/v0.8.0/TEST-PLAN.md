# Kolega Client v0.8.0 — Tests still required

1. JDK 21: run `narzedzia/testy/sprawdz-v080-windows.bat` from the full source ZIP.
2. Run Gradle build with Minecraft 1.21.10/Fabric and inspect the logs.
3. Confirm no client crash or shader conflict with Zoom.
4. Armor Status: empty slots and nearly broken armor.
5. Inventory HUD: first/last occupied slot, full stacks, no inventory mutation.
6. Potions HUD: multiple effects, times, no effects.
7. Target HUD: actual player under crosshair, disappearing target, GUI preview.
8. Notifications: toggling modules, 4-item max, fade, no spam on profile load.
9. HUD Editor: drag, reset, restart and JSON persistence.
10. Existing multiplayer, profiles, hotkeys and smooth Zoom.
11. Windows EXE + UpdateProtocol/Updater signature verification and rollback.
12. v0.7.1 → v0.8.0 signed update using legacy JAR compatibility shim.

**No automatic releases until the checklist is complete.**
