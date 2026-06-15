# Create: Balanced Flight

Create: Balanced Flight is a Create addon that adds progression-friendly flight tools for survival play.

The mod adds two main items:

- Flight Anchor: a kinetic block powered by Create rotation. When it receives enough speed, it enables flight in a configurable radius and renders a beacon-style beam.
- Ascended Flight Ring: a Curios-compatible ring item used by the flight system.

## Minecraft 1.21.1 NeoForge Port

The Minecraft 1.21.1 NeoForge port in this codebase was implemented by Vice.
This repository is xs_267's fork of themarneilx/BalancedFlight, hosted at
https://github.com/qwer854645/BalancedFlight_sable, and may include further changes.

Target versions for this branch:

- Minecraft `1.21.1`
- NeoForge `21.1.228`
- Create `6.0.10`
- Flywheel `1.0.6`
- Ponder `1.0.82`
- Curios API `9.5.1+1.21.1`
- GeckoLib `4.8.4`

The port updates the project from the old Minecraft 1.20.1 Forge setup to NeoForge's 1.21.1 mod loading, event, data generation, network, config, Curios, and renderer APIs.

## Features

- Create-powered Flight Anchor block
- Flight radius controlled by the anchor system
- Beacon-style beam when the Flight Anchor is active
- Animated GeckoLib block and item rendering
- Create shaft connection support on the Flight Anchor's side faces
- Curios ring slot support for the Ascended Flight Ring
- Create-style tooltips, recipes, and Ponder integration
- Optional [Sable](https://modrinth.com/mod/sable) compatibility: flight anchors work on physics structures and sub-levels, with `sable:mass` block properties

## Optional Mod Compatibility

### Sable

When Sable is installed, flight anchors:

- Enable flight in the overworld or inside Sable plot grids (not only the overworld)
- Measure range using Sable sub-level-aware coordinates, so anchors on moving physics structures still cover nearby players
- Register `data/balancedflight/physics_block_properties/flight_anchor.json` with a default mass for Sable physics

Sable is an optional dependency; Balanced Flight works without it.

## Attribution

This repository is a fork of themarneilx/BalancedFlight.

- Early upstream author: DenisMasterHerobrine (2019)
- Original project by Txni: https://github.com/txnimc/BalancedFlight
- Original CurseForge page: https://www.curseforge.com/minecraft/mc-mods/create-balanced-flight
- Create 6.0 update contributions: Sintinium — https://www.curseforge.com/members/sintinium/projects
- Create 6.0 fork maintenance: skadlig — https://github.com/skadlig/BalancedFlight
- Minecraft 1.21.1 NeoForge port implementation: Vice (`com.vice.balancedflight`)
- Immediate parent fork (Create 6.0.10 + NeoForge 1.21.1): themarneilx — https://github.com/themarneilx/BalancedFlight
- Current fork maintainer: xs_267 — https://github.com/qwer854645/BalancedFlight_sable

GitHub fork lineage: `txnimc` → `skadlig` → `themarneilx` → `qwer854645/BalancedFlight_sable`

See `LICENSE` and `NOTICE` for the full copyright and attribution chain.

## AI-Assisted Development

Parts of this project were created and modified with AI assistance, including
code changes, documentation, localization, and repository maintenance. Human
review and testing remain the responsibility of the maintainer.

### AI 辅助创作说明

本项目的部分内容在 AI 辅助下完成，包括但不限于代码修改、文档撰写、语言包整理
与仓库维护。最终修改与测试由维护者负责审核与确认。

## License

This project is licensed under the [MIT License](LICENSE).

You may use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of this software, provided that the copyright notice and permission
notice in `LICENSE` are included in all copies or substantial portions of the
software.

See also `NOTICE` for upstream attribution, fork history, and third-party
dependency notes.

### 开源协议（中文说明）

本项目采用 MIT 许可证发布。你可以自由使用、修改、分发本软件，包括用于商业用途，
但需要在再分发时保留原始版权声明和 MIT 许可全文。

本仓库为上游项目 Create: Balanced Flight 的 fork 与移植版本，并非上游官方仓库。
本项目直接基于 themarneilx/BalancedFlight 分叉开发；NeoForge 1.21.1 移植代码由 Vice 实现，
xs_267 为当前维护者。
再分发或二次开发时，请同时保留 `LICENSE` 与 `NOTICE` 中的上游及 fork 归属信息。

## Building

Use Java 21.

```bash
./gradlew build
```

The compiled jar is written to:

```text
build/libs/
```

For this porting workspace, the known-good build command is:

```bash
JAVA_HOME=/tmp/temurin-jdk21 PATH=/tmp/temurin-jdk21/bin:$PATH ./gradlew build
```

## Development Notes

- The Flight Anchor uses GeckoLib for the animated block model and a Create-safe renderer for shaft partials.
- The Flight Anchor advertises shafts on its side faces and uses the matching side-axis rotation so it connects naturally with Create shafts.
- Curios data is provided under `data/balancedflight/curios` and item tags under `data/curios/tags/item`.
- Generated data is written under `src/generated/resources`.
