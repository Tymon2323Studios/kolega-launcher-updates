# Kolega Launcher — podpisane aktualizacje przez GitHub Pages

## Obecny stan — 2026-10-14
- ✅ Repozytorium `Tymon2323Studios/kolega-launcher-updates` i GitHub Pages działają (ostatnia publikacja zakończona sukcesem).
- ✅ Workflow wykonuje testy weryfikacji oraz sprawdza RSA-PSS, SHA-256 i format archiwum.
- ✅ Przygotowany jest lokalny pakiet źródłowy v0.8.0 z narzędziami **PRZYGOTUJ-UPDATE.bat** i **OPUBLIKUJ-UPDATE.bat** (udostępniony w rozmowie, nie ma tu jeszcze plików EXE).
- ⚠️ **Brak obecnie publicznego release.json i paczki wykonywalnej**. Nie ma nowej aktualizacji do pobrania, dopóki nie przejdzie Windows build, test w grze i świadoma publikacja.

## Jednorazowa konfiguracja starego launchera v0.7.1
W zakładce **AKTUALIZACJE** należy zapisać adres:
`https://tymon2323studios.github.io/kolega-launcher-updates/release.json`

v0.8.0 używa tego oficjalnego adresu domyślnie na nowej instalacji; istniejące własne ustawienia nie są nadpisywane. Przed pierwszym wydaniem manifest nie istnieje (404).

## Pierwsze wydanie v0.8.0: bezpieczna procedura
1. Na Windows z JDK 21 i .NET 10 SDK uruchom **PRZYGOTUJ-UPDATE.bat** z kompletnych źródeł v0.8.0. Podaj ścieżkę do prywatnego pliku PEM znajdującego się **poza projektem**. Nie przesyłaj klucza przez czat ani GitHub.
2. Skrypt buduje najnowszy JAR, samodzielny launcher i updater, uruchamia testy, podpisuje manifest i weryfikuje archiwum. Tworzy `dist/ready-for-github-v080/release.json` oraz `KolegaLauncher-update.zip`. **Nie publikuje**.
3. Testuj v0.8.0 na Windows w prawdziwym Fabric Minecraft 1.21.10, sprawdź profile, nowe visuale, Zoom, updater i próbny rollback. Zachowaj kopię działającej v0.7.1.
4. Po potwierdzeniu wszystkich testów uruchom **OPUBLIKUJ-UPDATE.bat** (wymaga Git for Windows z uwierzytelnieniem). Wpisz `PUBLIKUJ`. Skrypt ponownie weryfikuje podpis, wstawia oba pliki do `site/` i wykonuje **jeden** commit/push do `main`.
5. Zaczekaj na zielony workflow w [Actions](https://github.com/Tymon2323Studios/kolega-launcher-updates/actions); sprawdź bezpośrednie adresy Pages. Dopiero wtedy v0.7.1 może zaoferować pobranie v0.8.0.

## Zgodność ze starym updaterem
Updater w v0.7.1 wymaga dosłownie `ClientFiles/kolega-client-0.7.1.jar`. Dlatego w pierwszej paczce muszą być **oba** pliki: `ClientFiles/kolega-client-0.8.0.jar` oraz dodatkowa kopia nowego JAR o starszej nazwie. Gra używa wyłącznie nowej wersji. W przyszłych aktualizacjach updater v0.8.0 sprawdza JAR zgodnie z wersją w podpisanym manifeście.

## Zabezpieczenia i ograniczenia
- Prywatnego klucza PEM **nigdy nie dodajemy** do GitHub repo, GitHub Secrets, paczki ZIP czy logów. Publiczny klucz w `tools/updater-public-key.pem` służy tylko do weryfikacji.
- Manifest i ZIP muszą być opublikowane razem i przejść RSA-PSS/SHA-256.
- ZIP w repo nie może przekraczać **100MB** na pojedynczy plik Git.
- GitHub Pages jest serwerem plików HTTPS, nie systemem budowania Windowsowego EXE. Rzeczywiste wydanie należy najpierw zbudować, podpisać i przetestować.
- To **aktualizacja dostępna z launchera**, z automatycznym sprawdzaniem przy uruchomieniu oraz instalacją po kliknięciu; nie wymuszamy bezgłośnej instalacji.
