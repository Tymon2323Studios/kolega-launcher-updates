#!/usr/bin/env python3
"""Validate unsigned release metadata and archive before Pages deployment.
This script does NOT verify the RSA signature. The publisher and client must."""
import base64
import hashlib
import json
from pathlib import Path
import re
import sys
from urllib.parse import urlparse
import zipfile

def validate(site: Path):
    manifest = site / "release.json"
    archive = site / "releases" / "KolegaLauncher-update.zip"
    if not manifest.exists() and not archive.exists():
        print("[INFO] No release published yet; landing page only.")
        return
    if not manifest.is_file() or not archive.is_file():
        raise ValueError("A release needs BOTH release.json and the ZIP.")
    if manifest.stat().st_size > 65536:
        raise ValueError("Manifest too large.")
    envelope = json.loads(manifest.read_text(encoding="utf-8"))
    if set(envelope) != {"payload", "signature"}:
        raise ValueError("Signed envelope requires payload and signature.")
    signature = base64.b64decode(envelope["signature"], validate=True)
    if len(signature) < 256:
        raise ValueError("Missing/short signature.")
    payload = base64.b64decode(envelope["payload"], validate=True)
    if len(payload) > 32768:
        raise ValueError("Payload too large.")
    info = json.loads(payload)
    if not re.fullmatch(r"\d+\.\d+\.\d+", info.get("version", "")):
        raise ValueError("Invalid semantic version.")
    uri = urlparse(info.get("packageUrl", ""))
    if (uri.scheme != "https" or uri.hostname != "tymon2323studios.github.io"
        or uri.path != "/kolega-launcher-updates/releases/KolegaLauncher-update.zip"
        or uri.username or uri.password or uri.query or uri.fragment):
        raise ValueError("Package URL must be on the official GitHub Pages site.")
    size = archive.stat().st_size
    if size <= 0 or size > 650 * 1024 * 1024 or size != info.get("sizeBytes"):
        raise ValueError("Package size mismatch/limit.")
    h = hashlib.sha256()
    with archive.open("rb") as stream:
        while part := stream.read(1024 * 1024):
            h.update(part)
    if h.hexdigest().lower() != info.get("sha256", "").lower():
        raise ValueError("Package SHA256 mismatch.")
    with zipfile.ZipFile(archive) as z:
        entries = z.infolist()
        if not 2 <= len(entries) <= 3000:
            raise ValueError("Invalid archive entry count.")
        names = set()
        total = 0
        for e in entries:
            name = e.filename
            if (not name or name.startswith("/") or "\\" in name or ":" in name
                or "\x00" in name or any(p in (".", "..") for p in name.split("/"))):
                raise ValueError("Unsafe archive path.")
            lower = name.lower()
            if lower in names:
                raise ValueError("Duplicate archive path.")
            names.add(lower)
            if (e.external_attr >> 16) & 0xF000 == 0xA000:
                raise ValueError("Symlinks not supported.")
            total += e.file_size
            if total > 2 * 1024 * 1024 * 1024:
                raise ValueError("Archive too large when unpacked.")
        required = {"kolegaLauncher.exe".lower(),
                    "Updater/KolegaUpdater.exe".lower(),
                    "ClientFiles/kolega-client-0.6.0.jar".lower()}
        if not required.issubset(names):
            raise ValueError("Required launcher/Updater/Client JAR missing.")
    print("[OK] Structure, URL, size, ZIP paths and SHA-256 verified.")
    print("[REMINDER] Verify RSA-PSS signature with publisher/client separately.")

if __name__ == "__main__":
    try:
        validate(Path(sys.argv[1] if len(sys.argv) > 1 else "site"))
    except Exception as e:
        print("[ERROR]", e, file=sys.stderr)
        sys.exit(1)
