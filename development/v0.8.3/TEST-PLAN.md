# Kolega Client v0.8.3 — release gate

### Zrobione lokalnie
- [x] Java 21: 10 modułów, HitTracker, VisualSettings i 2 mixiny — kompilacja stub API
- [x] Java 21: ClickGUI i ConfigManager z testami GUI (6 rozdzielczości)
- [x] VisualSettings: 10 definicji, slider clamps, cycling, restore, walidacja złych pól
- [x] Statyczny audyt spójności numeru wersji v0.8.3, instalatora i ścieżek JAR

### Wymagane na Windows / Minecraft 1.21.10
- [ ] Pełny Gradle/Fabric i .NET 10 build
- [ ] Full Bright: jaskinia, opcje gamma, przywrócenie wartości
- [ ] No Fire: ogień ON/OFF bez anulowania obrażeń
- [ ] No Weather: rzeczywisty deszcz i śnieg
- [ ] ArrayList: motywy, skala i pozycje HUD po restarcie
- [ ] Combo + Reach: udane/nieudane trafienia, opóźnienia, reset
- [ ] Hit Sounds: poziomy głośności i spójność z detekcją trafień
- [ ] Hit Particles: trzy tryby, duże FPS, limit
- [ ] Jump Circles: skoki, poruszanie po wodzie, creative/spectator
- [ ] Target Render: Legacy/Ghost/Circle/Crystal i brak błędów renderowania
- [ ] ClickGUI, Crosshair, Smooth Zoom, keybindy, HUD Editor, profile, multiplayer
- [ ] Backup i udany rollback oraz podpisany v0.8.0 → v0.8.3 update na kopii

**Nie uruchamiać `OPUBLIKUJ-UPDATE.bat` przed zaliczeniem prawdziwych testów. Klucza PEM nigdy nie commitować.**
