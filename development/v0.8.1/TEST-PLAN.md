# Kolega Client v0.8.1 test matrix

1. **Build**: JDK 21 + .NET 10 SDK; launch `narzedzia/testy/sprawdz-v081-windows.bat`, then full launcher installer from package.
2. **GUI**: RSHIFT / ESC open-close, no pause, no click-through.
3. **Navigation**: VISUALS, HUD, MOVEMENT, FAVORITES, HOTKEYS, WYGLAD; at reduced GUI sizes scroll sidebar.
4. **Themes**: all 12, including Light Clean legibility.
5. **Customization**: RGB and custom toggle, 45/100 opacity, 0/22 radius, animation 50/200%, glow/shadow/gradient toggles, density choices.
6. **Search + Favorites**: type and delete text, switching categories, favorite add/remove.
7. **Bindings**: capture/cancel/backspace/conflicts; Zoom 2–8x.
8. **Persistence**: restart, verify GUI presets 1–3 and legacy HUD presets 1–3.
9. **Regression**: Smooth Zoom, multiplayer and v0.8.0 HUD modules.
10. **Release**: build signed v0.8.1; verify signed manifest/hash, disposable v0.8.0→v0.8.1 update and rollback, then only publish.
