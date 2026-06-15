# Create: Balanced Flight

Create: Balanced Flight is a Create addon that adds progression-friendly flight tools for survival play.

The mod adds two main items:

- Flight Anchor: a kinetic block powered by Create rotation. When it receives enough speed, it enables flight in a configurable radius and renders a beacon-style beam.
- Ascended Flight Ring: a Curios-compatible ring item used by the flight system.

## Minecraft 1.21.1 NeoForge Port

This branch ports the mod to:

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

## Attribution

This repository is a fork and port of Create: Balanced Flight.

- Original project by Txni: https://github.com/txnimc/BalancedFlight
- Original CurseForge page: https://www.curseforge.com/minecraft/mc-mods/create-balanced-flight
- Create 6.0 update contributions: Sintinium — https://www.curseforge.com/members/sintinium/projects
- Minecraft 1.21.1 NeoForge port: xs_267 — https://github.com/xs_267/BalancedFlight

Early upstream copyright is attributed to DenisMasterHerobrine (2019). See
`LICENSE` and `NOTICE` for the full copyright and attribution chain.

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
再分发或二次开发时，请同时保留 `LICENSE` 与 `NOTICE` 中的上游归属信息。

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
