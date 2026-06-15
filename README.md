# Create: Balanced Flight (Sable Fork)

[中文](README_zh.md)

A fork of [themarneilx/BalancedFlight](https://github.com/themarneilx/BalancedFlight) for NeoForge 1.21.1 + Create 6.0.10, with the changes below applied in this repository.

**Repository:** https://github.com/qwer854645/BalancedFlight_sable  
**Downloads:** [Releases](https://github.com/qwer854645/BalancedFlight_sable/releases)

## Changes in This Fork

### 1. Full Translation Key Coverage

UI and descriptive text now use translation keys for easier maintenance and localization:

- Item and block tooltips
- Ponder scene text
- Language files: `en_us.json`, `zh_cn.json`

### 2. Elytra Features Removed

Streamlined to a creative-flight setup centered on the **Flight Anchor** and **Ascended Flight Ring**. Removed:

- Elytra takeoff keybinds and input handling
- Elytra-related Mixins and network packets
- Config options such as infinite fireworks and elytra ascension

Flight anchor RPM-based range flight and ring-granted flight remain.

### 3. Sable Physics Compatibility (Optional)

When [Sable](https://modrinth.com/mod/sable) is installed:

- Flight anchors work in the overworld or inside Sable plot grids
- Distance checks use sub-level-aware coordinates for anchors on moving physics structures
- Ships `data/balancedflight/physics_block_properties/flight_anchor.json` (default `sable:mass: 4.0`)

Without Sable, behavior matches the base mod. Sable is an optional dependency.

## Requirements

| Component | Version |
|-----------|---------|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.228 |
| Create | 6.0.10 |
| Curios API | 9.5.1+1.21.1 |
| GeckoLib | 4.8.4 |
| Sable | 1.2.0+ (optional) |

## Building

Requires Java 21:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.10"
.\gradlew build
```

Output is written to `build/libs/`.

## Attribution & License

This is an unofficial fork of upstream Create: Balanced Flight, licensed under MIT. See `LICENSE` and `NOTICE` for the full copyright chain.

Some changes were made with AI assistance and reviewed by the maintainer.
