# v0.8.3 DEV — Hit Sounds compilation fix (Minecraft 1.21.10)

The full Minecraft 1.21.10/Fabric Windows Gradle build exposed **two Java errors** in `pl/kolegaclient/visual/HitTracker.java`, lines 72 and 73:

```
Reference<SoundEvent> cannot be converted to SoundEvent
```

Mojang-mapped Minecraft 1.21.10 declares `SoundEvents.NOTE_BLOCK_PLING` and `SoundEvents.UI_BUTTON_CLICK` as `Holder.Reference<SoundEvent>`. The `LocalPlayer.playSound(...)` call requires a `SoundEvent`.

**Only the two affected statements change:**

```java
case 1 -> game.player.playSound(SoundEvents.NOTE_BLOCK_PLING.value(),volume,pitch);
case 2 -> game.player.playSound(SoundEvents.UI_BUTTON_CLICK.value(),volume,pitch);
default -> game.player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP,volume,pitch);
```

The original stub-based preflight mistakenly represented both references as regular `SoundEvent` instances. The fixed **complete v0.8.3 project** (provided as ZIP in the conversation) also replaces that test stub with realistic `Holder.Reference<SoundEvent>` fields, adds `tools/test-hit-sounds-12110.py`, and runs the regression test from `narzedzia/testy/sprawdz-v083-windows.bat`.

**Local verification:** new Java 21 stub build and regression test, version/configuration checks and GUI smoke tests passed; complete Gradle/Fabric build on Windows must be rerun. This is **developer-only**; DO NOT publish a signed release until in-game testing confirms success. Existing production v0.8.0 is unchanged.

Map: https://mappings.dev/1.21.10/net/minecraft/sounds/SoundEvents.html
