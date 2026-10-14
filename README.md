# Kolega Launcher — signed update channel 💜

**Minecraft Java 1.21.10 · Fabric · .NET 10 WPF launcher**

## Status
- **v0.7.1 Smooth Zoom:** installed and gameplay-tested by the project team.
- **GitHub update hosting:** source, manifest validator, and workflow are configured; 8 validator tests pass on GitHub Actions.
- **GitHub Pages:** must be enabled by repository owner at [Settings → Pages](https://github.com/Tymon2323Studios/kolega-launcher-updates/settings/pages) with **Source: GitHub Actions**. The deploy job is expected to fail at **Configure Pages** until enabled.
- **Current release:** none. Do not treat this as a live updater until the signed release.json and matching tested ZIP have been published.

## Existing v0.7.1 launcher (one-time setup)
Inside **AKTUALIZACJE**, save:
https://tymon2323studios.github.io/kolega-launcher-updates/release.json

Until the first tested release (planned v0.8.0), a 404 at that URL is expected. This is **not** evidence of a broken v0.7.1 installation.

## Safety
The launcher pins an RSA public key and verifies RSA-PSS/SHA256 signatures, exact download size, SHA256 hash and required ZIP entries. The GitHub workflow validates release signatures and compatibility with the **v0.7.1 legacy updater**, which expects an extra ClientFiles/kolega-client-0.7.1.jar in the first new release.

Never upload private PEM signing keys, access tokens, accounts, or Minecraft player data.

**Publisher instructions:** [RELEASE-PROCESS.md](RELEASE-PROCESS.md).  
**Validation:** [tools/validate_release.py](tools/validate_release.py).  
**Development code previews:** [development/](development/).
