# Kolega Client 0.8.6 — Hands & Animation Overhaul

TymonIndustrials · Minecraft Java 1.21.10 / Fabric / JDK 21 · WPF .NET 10.

Source-only developer branch. The **full source ZIP** is shared in the project conversation; this GitHub Pages update-channel repository is not a substitute for the full launcher source. **NO PUBLIC RELEASE yet**. The installed launcher will show plain **0.8.6**, without a separate DEV label.

## Four visual modules

- **Hand Tweaks:** per-hand X/Y/Z offsets and Euler rotations; shared scaling and presets Custom/Classic/Compact/Low/Center. First-person PoseStack only, not server-side physics.
- **Swing Animations:** Vanilla/Classic/Smooth/Slide/Spin, strength/speed and main/off/both hands. Additive, client-only matrix animation; does not speed up attacks.
- **Use Animations:** Vanilla/Bounce/Float/Lower/Sway for visible use pose, main/off/both hands. Normal item use and packets are unchanged.
- **Shaded Hands:** Glow enables full-bright hand/item packed light, with configurable screen-space first-person chroma/snow/glass accents. **Not a true material shader or transparent skin texture** — these remain a separate advanced renderer milestone.

All four are OFF by default, use shared scrollable ClickGUI sliders/modes, and save to the existing JSON `visualSettings` with bounds checks and migration. Earlier 21 settings sets are retained, 25 total.

## Version-display fix

The launcher WPF title, sidebar, Update and About panels read the actual compiled assembly version using `typeof(MainWindow).Assembly.GetName().Version`. This fixes older static `v0.8.3` text left in XAML. The Fabric ClickGUI header gets its version from mod metadata instead of hard-coded Java. Active JAR paths and installation scripts use `kolega-client-0.8.6.jar`, including the older installer helper that was accidentally left on 0.8.5. No separate DEV label is shown in the program.

## Verification carried out

- Java 21 tests of 25 settings definitions, numeric clamps, migration/persistence, independent hand transformations and swing/use easing.
- Java 21 stub compilation of new first-person Mixin and all four module classes, plus checking instance callback declarations via javap.
- Existing v0.8.5 Java API smoke tests, ClickGUI at six sizes, Crosshair, HUD/scale, Hit Sounds and settings regression.
- WPF XAML XML parse, launcher version labels and installer-path checks.
- This is **NOT** a completed Gradle/Fabric or Windows/.NET 10 build, and no Minecraft runtime launch happened in this environment.

**Public `main/site/release.json` is NOT changed**. Both the BAT and PowerShell publishing entrypoints in the full source archive are disabled. Verify Windows Fabric build and gameplay before any update release.

See [TEST-PLAN.md](TEST-PLAN.md).