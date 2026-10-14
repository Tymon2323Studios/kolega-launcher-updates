# Kolega Launcher — podpisany kanał automatycznych aktualizacji 💜

Minecraft Java **1.21.10** · Fabric · .NET 10 WPF.

## Status (2026-10-14)

| Funkcja | Stan |
|---|---|
| Publiczny hosting GitHub Pages (HTTPS) | ✅ aktywny |
| Workflow GitHub Actions | ✅ publikuje stronę |
| Walidator podpisu RSA-PSS, SHA256 i paczki | ✅ aktywny |
| v0.7.1 Smooth Zoom w grze | ✅ przetestowana przez zespół |
| v0.8.0 Visuals Stage 1 | 🧪 wymaga prawdziwego buildu i testu Minecraft/Fabric |
| Podpisany plik release.json / ZIP aktualizacji | ⏳ **jeszcze nie opublikowano** |

### Jeden adres do wpisania w aktualnym launcherze v0.7.1
`https://tymon2323studios.github.io/kolega-launcher-updates/release.json`

Dopóki nie opublikowano pierwszego wydania, **404 jest normalne**. Nie należy wgrywać wymyślonej lub niepodpisanej paczki.

### Publikacja pierwszej aktualizacji
W kompletnym pakiecie źródeł v0.8.0 przygotowano dwa skrypty do Windows: `PRZYGOTUJ-UPDATE.bat` (budowa + podpis + testy; bez publikacji), a po **udanych testach w grze** `OPUBLIKUJ-UPDATE.bat` (jednoczesny commit manifestu i ZIP na GitHub).

**Instrukcja:** [RELEASE-PROCESS.md](RELEASE-PROCESS.md).  
**Walidator:** [tools/validate_release.py](tools/validate_release.py).  
**Rozwój visuali:** [development/](development/).

Nigdy nie przesyłaj ani nie commituj prywatnego klucza podpisywania.
