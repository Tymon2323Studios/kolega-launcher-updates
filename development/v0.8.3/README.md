# Kolega Client v0.8.3 — Visuals Expansion (DEV)

**TymonIndustrials · Minecraft Java 1.21.10 · Fabric · Java 21**

Ten katalog to dokumentacja developerska oraz kod pomocniczy nowego wydania. **Pełne źródła Java + WPF (.NET 10), wraz z instalatorem Windows, są w osobnym ZIP-ie przekazanym w rozmowie ChatGPT**. Gałąź ta nie publikuje GitHub Pages.

## 10 modułów

1. **Full Bright:** lokalne podniesienie gamma do maksimum vanilla, automatyczne przywrócenie poprzedniej jasności
2. **No Fire:** Mixin wyłączający wyświetlanie ognia na ekranie, bez wyłączania obrażeń
3. **No Weather:** Mixin wyłączający klientowe renderowanie opadów, bez zmiany serwerowej pogody
4. **ArrayList HUD:** lista aktywnych modułów, własny kolor, odstępy, przesuwanie i skalowanie
5. **Combo Counter:** seria trafień szacowana na podstawie lokalnie obserwowanych hurtTime, z resetem
6. **Reach Display:** orientacyjny dystans od oka do granicy hitboxu zarejestrowanego celu
7. **Hit Sounds:** trzy lokalne dźwięki vanilla, regulacja głośności i wysokości
8. **Hit Particles:** limitowane cząsteczki Crit/Magic/Hearts
9. **Jump Circles:** klientowe okręgi cząsteczek podczas skoku, promień/gęstość/efekt
10. **Target Render:** cztery tryby pierścieni/spirali z cząsteczek: Legacy/Ghost/Circle/Crystal

**Ważne:** dane Combo/Reach nie są statystykami serwera; lokalnie obserwowany stan obrażeń bywa opóźniony. Full Bright to na tym etapie standardowa gamma, nie niezależny mechanizm Night Vision. No Fire/No Weather wymagają testu rzeczywistych metod renderera Minecraft 1.21.10.

### Integracja
- Wspólne `VisualSettings` z definicjami suwaków/trybów i bezpiecznymi zakresami.
- Nowy inspektor `ClickGuiScreen` generuje kontrolki na podstawie definicji i przewija listę.
- `ConfigManager` zapisuje `visualSettings` w starym `kolega-client.json`.
- Moduły zarejestrowane przez `ModuleManager`, efekty trafień monitorowane przez `HitTracker`.
- Zachowano ClickGUI v0.8.2, Zoom, Crosshair Studio, HUD Editor i multiplayer.

**Stan:** przeszły testy kompilacji Java 21 przeciw mock API oraz testy ustawień. **Pełny Gradle/Fabric, WPF i uruchomienie w grze NIE są jeszcze potwierdzone.** Bez publikacji `site/release.json`/ZIP do końca testów.

Kod i testy w tym repozytorium to tylko fragmenty; kompletne źródła znajdują się w ZIP-ie rozmowy.
