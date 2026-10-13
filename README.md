# Kolega Launcher — Update Channel 💜

Publiczny kanał aktualizacji **Kolega Launcher** (Minecraft Java 1.21.10, Fabric).

Ta gałąź zawiera wyłącznie pliki niezbędne do hostowania aktualizacji. **Nie zawiera gry Minecraft, kluczy prywatnych, kont, tokenów ani zapisów światów.**

## Status
- Repozytorium i infrastruktura: przygotowywane.
- Obecna działająca wersja u użytkowników: v0.6.1.1.
- Updater v0.6.2: wymaga potwierdzonego buildu i testu na Windows.
- **Brak opublikowanego release.json — nie ma jeszcze automatycznych aktualizacji do pobrania.**

## GitHub Pages
Po włączeniu **Settings → Pages → Source: GitHub Actions** strona informacyjna będzie dostępna pod:
https://tymon2323studios.github.io/kolega-launcher-updates/

Manifest (dopiero po publikacji sprawdzonego, podpisanego wydania):
https://tymon2323studios.github.io/kolega-launcher-updates/release.json

## Publikacja
Do `site/releases/` dodaje się wyłącznie **przetestowane**, poprawnie podpisane paczki aktualizacji.
Manifest musi zawierać podpis RSA-PSS/SHA-256 zgodny z kluczem publicznym osadzonym w updaterze.
Walidator `tools/validate_release.py` sprawdza strukturę ZIP, URL, sumy i rozmiary; podpis musi być dodatkowo sprawdzony przez launcher/narzędzie wydawcy.

**Nigdy nie commituj prywatnego klucza podpisywania.**
