#!/usr/bin/env python3
"""Check RSA-PSS signature, SHA-256, ZIP and v0.7.1 compatibility before publishing."""
import base64
import binascii
import hashlib
import json
from pathlib import Path
import re
import subprocess
import sys
import tempfile
from urllib.parse import urlsplit
import zipfile

PUBKEY = Path(__file__).with_name('updater-public-key.pem')
PACKAGE_URL = 'https://tymon2323studios.github.io/kolega-launcher-updates/releases/KolegaLauncher-update.zip'
MAX_GIT_BLOB = 100_000_000
LEGACY_JAR = 'ClientFiles/kolega-client-0.7.1.jar'

def signed_payload(path, public_key):
    if path.stat().st_size > 65536:
        raise ValueError('Manifest too large')
    outer = json.loads(path.read_text(encoding='utf-8'))
    if not isinstance(outer, dict) or set(outer) != {'payload', 'signature'}:
        raise ValueError('Expected payload and signature')
    try:
        payload = base64.b64decode(outer['payload'], validate=True)
        signature = base64.b64decode(outer['signature'], validate=True)
    except (TypeError, ValueError, KeyError, binascii.Error) as exc:
        raise ValueError('Invalid base64 manifest fields') from exc
    if not payload or len(payload) > 32768 or not 256 <= len(signature) <= 1024:
        raise ValueError('Invalid payload/signature size')
    with tempfile.TemporaryDirectory(prefix='kolega-verify-') as tmp:
        datafile = Path(tmp) / 'payload'
        sigfile = Path(tmp) / 'signature'
        datafile.write_bytes(payload)
        sigfile.write_bytes(signature)
        try:
            result = subprocess.run(
                ['openssl', 'dgst', '-sha256', '-verify', str(public_key),
                 '-sigopt', 'rsa_padding_mode:pss', '-sigopt', 'rsa_pss_saltlen:-1',
                 '-signature', str(sigfile), str(datafile)],
                capture_output=True, text=True, timeout=15, check=False)
        except (FileNotFoundError, subprocess.TimeoutExpired) as exc:
            raise ValueError('RSA verification unavailable') from exc
        if result.returncode != 0 or 'Verified OK' not in result.stdout:
            raise ValueError('RSA-PSS signature verification failed')
    info = json.loads(payload)
    if not isinstance(info, dict):
        raise ValueError('Signed payload must be an object')
    return info

def validate(site, public_key=PUBKEY):
    manifest = site / 'release.json'
    archive = site / 'releases' / 'KolegaLauncher-update.zip'
    if not manifest.exists() and not archive.exists():
        print('[INFO] No release published; landing page only.')
        return False
    if not manifest.is_file() or not archive.is_file():
        raise ValueError('Release requires BOTH manifest and ZIP')
    info = signed_payload(manifest, public_key)
    version = info.get('version')
    if not isinstance(version, str) or not re.fullmatch(r'\d+\.\d+\.\d+', version):
        raise ValueError('Invalid version')
    if tuple(map(int, version.split('.'))) <= (0, 7, 1):
        raise ValueError('Release must be newer than installed v0.7.1')
    url = info.get('packageUrl')
    if not isinstance(url, str) or url != PACKAGE_URL:
        raise ValueError('Package URL must match canonical Pages URL (no redirects)')
    parts = urlsplit(url)
    if parts.scheme != 'https' or parts.username or parts.password or parts.query or parts.fragment:
        raise ValueError('Unsafe URL')
    size = archive.stat().st_size
    if not 0 < size <= MAX_GIT_BLOB:
        raise ValueError('GitHub ZIP size limit is 100MB')
    if type(info.get('sizeBytes')) is not int or info['sizeBytes'] != size:
        raise ValueError('Signed ZIP size mismatch')
    sha = info.get('sha256')
    if not isinstance(sha, str) or not re.fullmatch(r'[a-fA-F0-9]{64}', sha):
        raise ValueError('Bad SHA256 syntax')
    digest = hashlib.sha256()
    with archive.open('rb') as stream:
        for part in iter(lambda: stream.read(1024 * 1024), b''):
            digest.update(part)
    if digest.hexdigest().lower() != sha.lower():
        raise ValueError('SHA256 mismatch')
    with zipfile.ZipFile(archive) as zipfileobj:
        entries = zipfileobj.infolist()
        if not 2 <= len(entries) <= 3000:
            raise ValueError('Bad ZIP entry count')
        names = set()
        extracted = 0
        for e in entries:
            name = e.filename
            if (not name or name[0] in ('/', '\\') or '\\' in name or ':' in name
                or '\x00' in name or any(p in ('.', '..') for p in name.split('/'))):
                raise ValueError('Unsafe ZIP path')
            canonical = name.casefold()
            if canonical in names:
                raise ValueError('Duplicate ZIP path')
            names.add(canonical)
            if ((e.external_attr >> 16) & 0xF000) == 0xA000:
                raise ValueError('Symlink in ZIP')
            extracted += e.file_size
            if extracted > 2 * 1024**3:
                raise ValueError('ZIP uncompressed size too large')
        required = {'KolegaLauncher.exe'.casefold(), 'Updater/KolegaUpdater.exe'.casefold(),
                    ('ClientFiles/kolega-client-' + version + '.jar').casefold(),
                    LEGACY_JAR.casefold()}
        if not required.issubset(names):
            raise ValueError('ZIP missing launcher, updater, current or legacy v0.7.1 JAR')
    print('[OK] Signature, SHA256, URL, ZIP and v0.7.1 migration: ' + version)
    return True

if __name__ == '__main__':
    try:
        validate(Path(sys.argv[1]) if len(sys.argv) > 1 else Path('site'))
    except Exception as exc:
        print('[ERROR] ' + str(exc), file=sys.stderr)
        sys.exit(1)
