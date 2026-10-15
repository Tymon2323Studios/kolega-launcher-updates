#!/usr/bin/env python3
"""Test keys are ephemeral and unrelated to production publisher signing key."""
import base64, hashlib, json, subprocess, sys, unittest, zipfile
from pathlib import Path
from tempfile import TemporaryDirectory
sys.path.insert(0, str(Path(__file__).resolve().parents[1] / 'tools'))
from validate_release import validate, PACKAGE_URL

class ChannelTest(unittest.TestCase):
    def setUp(self):
        self.tmp = TemporaryDirectory(); self.addCleanup(self.tmp.cleanup)
        self.root = Path(self.tmp.name); self.private = self.root/'test-key.pem'; self.pub = self.root/'pub.pem'
        subprocess.run(['openssl','genpkey','-algorithm','RSA','-pkeyopt','rsa_keygen_bits:2048','-out',str(self.private)], check=True, capture_output=True)
        subprocess.run(['openssl','pkey','-in',str(self.private),'-pubout','-out',str(self.pub)], check=True, capture_output=True)
        self.site = self.root/'site'; (self.site/'releases').mkdir(parents=True)
        self.archive = self.site/'releases'/'KolegaLauncher-update.zip'
        self.manifest = self.site/'release.json'
    def publish(self, version='0.8.0', legacy=True, url=PACKAGE_URL, tamper=False):
        entries = ['KolegaLauncher.exe', 'Updater/KolegaUpdater.exe',
                   'ClientFiles/kolega-client-'+version+'.jar']
        if legacy: entries += ['ClientFiles/kolega-client-0.7.1.jar']
        with zipfile.ZipFile(self.archive,'w') as z:
            for name in entries: z.writestr(name,b'fake for structural tests only')
        data = json.dumps({'version':version,'packageUrl':url,'sizeBytes':self.archive.stat().st_size,
                           'sha256':hashlib.sha256(self.archive.read_bytes()).hexdigest(),'notes':'test'}).encode()
        payload=self.root/'payload'; payload.write_bytes(data); sig=self.root/'signature'
        subprocess.run(['openssl','dgst','-sha256','-sign',str(self.private),
                        '-sigopt','rsa_padding_mode:pss','-sigopt','rsa_pss_saltlen:-1',
                        '-out',str(sig),str(payload)],check=True,capture_output=True)
        if tamper: data += b' '
        self.manifest.write_text(json.dumps({'payload':base64.b64encode(data).decode(),
                                              'signature':base64.b64encode(sig.read_bytes()).decode()}))
    def test_empty(self): self.assertFalse(validate(self.site,self.pub))
    def test_valid(self):
        self.publish(); self.assertTrue(validate(self.site,self.pub))
    def test_valid_four_part(self):
        self.publish(version='0.9.5.2')
        self.assertTrue(validate(self.site, self.pub))
    def test_reject_five_part_version(self):
        self.publish(version='0.9.5.2.1')
        with self.assertRaisesRegex(ValueError, 'Invalid version'): validate(self.site, self.pub)
    def test_changed_payload(self):
        self.publish(tamper=True)
        with self.assertRaisesRegex(ValueError,'signature'): validate(self.site,self.pub)
    def test_changed_zip(self):
        self.publish(); b=bytearray(self.archive.read_bytes()); b[25] ^= 1; self.archive.write_bytes(b)
        with self.assertRaisesRegex(ValueError,'SHA256'): validate(self.site,self.pub)
    def test_missing_legacy_client(self):
        self.publish(legacy=False)
        with self.assertRaisesRegex(ValueError,'legacy'): validate(self.site,self.pub)
    def test_bad_url(self):
        self.publish(url='https://example.com/fake.zip')
        with self.assertRaisesRegex(ValueError,'canonical'): validate(self.site,self.pub)
    def test_old_version(self):
        self.publish(version='0.7.0')
        with self.assertRaisesRegex(ValueError,'newer'): validate(self.site,self.pub)
    def test_missing_archive(self):
        self.manifest.write_text('{}')
        with self.assertRaisesRegex(ValueError,'BOTH'): validate(self.site,self.pub)
if __name__ == '__main__': unittest.main(verbosity=2)
