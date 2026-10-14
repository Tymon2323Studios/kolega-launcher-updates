# Kolega Launcher — GitHub update channel (v0.7.1 → v0.8.0)

## One-time owner action
Open [Settings → Pages](https://github.com/Tymon2323Studios/kolega-launcher-updates/settings/pages) and choose **Build and deployment → Source: GitHub Actions**. The current workflow is known to fail at **Configure Pages** until this is done. GITHUB_TOKEN cannot auto-enable Pages; do not put a PAT or signing key in the repository.

## One-time action in each existing v0.7.1 launcher
In **Aktualizacje**, set and save:
https://tymon2323studios.github.io/kolega-launcher-updates/release.json

Until a signed release is published this URL returns 404; this is expected. v0.7.1 checks only the URL saved in its settings. When an update is published it will verify an RSA-PSS/SHA-256 signature before offering installation.

## Publishing a real v0.8.0 update
1. Build and test Fabric v0.8.0 JAR, self-contained win-x64 launcher v0.8.0 EXE, and standalone updater EXE on Windows. Confirm launcher's FileVersion = 0.8.0.
2. Prepare a flat publish folder with:
   - KolegaLauncher.exe (v0.8.0)
   - Updater/KolegaUpdater.exe
   - ClientFiles/kolega-client-0.8.0.jar
   - **ClientFiles/kolega-client-0.7.1.jar (compatibility shim)**
3. **Important**: the updater already shipped in v0.7.1 requires the 0.7.1 JAR by its literal filename. If this legacy file is absent it rejects the entire v0.8.0 archive *before* installation. The v0.8.0 launcher must reference and deploy the **new** 0.8.0 JAR; the old JAR stays unused in the package solely for compatibility.
4. Update future updater checks to derive the required JAR filename from the **signed manifest version** rather than from a build-time literal. Keep legacy compatibility file in release packages until no v0.7.1 installs remain.
5. Use the launcher source's offline **Publisher** tool with the PRIVATE PEM stored locally only. Publisher must be version-aware and include both JARs for this first upgrade. **Never commit/upload PEM, tokens, passwords, raw game files, or personal data.**
6. Local check: place *signed* release.json and ZIP in site/ and run \`python tools/validate_release.py site\`. CI repeats 8 negative/positive tests and validates PSS signature against the pinned trusted public key.
7. Publish these exact files to site/ in the repository if **ZIP ≤ 100 MB** (GitHub single Git object limit) and run GitHub Actions. If the compressed file is larger, stop and use a release-hosting approach with appropriate trusted redirects rather than bypassing the size limit.
8. Verify HTTPS Pages URLs return the expected contents; test a real v0.7.1 → v0.8.0 upgrade, restart handshake, rollback on induced failure, and saved HUD/profiles.

**No current release has been published**, so do not imply auto-updates already work. Do not serve unsigned JSON or an untested EXE.
