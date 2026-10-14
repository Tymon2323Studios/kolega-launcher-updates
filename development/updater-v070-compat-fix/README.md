# TymonIndustrials — compatibility repair for legacy v0.7.0 updater

Status: **developer preview; not published to main**.

## Root cause (verified against v0.7.0 source)
The launcher installed by Stachu is **v0.7.0**, not v0.7.1. The updater's `UpdateProtocol.VerifyArchive` explicitly requires `ClientFiles/kolega-client-0.7.0.jar` in the signed ZIP, and `KolegaUpdater.Program` checks the same name again before swapping directories.

The initial signed release 0.8.0 contains `ClientFiles/kolega-client-0.8.0.jar` and `ClientFiles/kolega-client-0.7.1.jar` (the legacy shim), but **not** the 0.7.0 shim. Hence the error "Paczka nie zawiera EXE, Updater lub Kolega Client JAR"; the client safely refuses to install.

## Minimal remedy
A separate **KolegaLauncher-v070-AUTOUPDATE-COMPAT-FIX.zip** toolkit, shared privately in the project conversation, can:
1. Download the original *signed* v0.8.0 manifest and archive from GitHub Pages.
2. Verify pinned RSA-PSS signature, SHA-256 and archive structure first.
3. Append exactly `ClientFiles/kolega-client-0.7.0.jar` to the existing archive, copying the **v0.8.0 JAR bytes**. The original EXE/Updater and all other entries are verified unchanged.
4. Sign a *new* manifest using the user's own locally stored private PEM (never upload it).
5. Re-verify and stage the results for explicit, separate publishing. Before push, verify the old GitHub manifest has not changed.

**The original v0.8.0 version and EXE remain identical.** Existing v0.8.0 installs will not be prompted to update; v0.7.0/0.7.1 are both supported. Because a same-version package is republished to the same URLs, clients may see temporary CDN cache inconsistencies; signed hash verification must remain enabled, and users retry after propagation.

## Release gate
- [x] Confirm hard-coded requirement from actual v0.7.0 source.
- [x] Offline synthetic ZIP tests of rejection/compatibility and byte-identical files.
- [x] Toolkit contains no signing key.
- [ ] Windows .NET 10 compile and full toolkit test.
- [ ] GitHub Pages new artifact deployment and RSA/SHA256 validation.
- [ ] Real updater v0.7.0 → v0.8.0 test on Stachu's computer, including profiles/multiplayer.

**Do not modify `main/site/release.json` or published ZIP until the new signed package has passed user-side verification.** v0.8.3 remains a separate unshipped developer version.
