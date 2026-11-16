# Create: Balanced Flight (Sable Fork)

[English](README.md)

基于 [themarneilx/BalancedFlight](https://github.com/themarneilx/BalancedFlight) 的 fork，面向 NeoForge 1.21.1 + Create 6.0.10，并针对本仓库做了以下改动。

**仓库：** https://github.com/qwer854645/BalancedFlight_sable  
**下载：** [Releases](https://github.com/qwer854645/BalancedFlight_sable/releases)

## 本 fork 的修改

### 1. 全面翻译键化

界面与说明文本改为使用翻译键，便于维护与本地化：

- 物品与方块 tooltip
- Ponder 场景文本
- 语言包：`en_us.json`、`zh_cn.json`

### 2. 移除鞘翅相关功能

精简为以**飞行锚 + 升华飞行戒指**为核心的创造模式飞行方案，已移除：

- 鞘翅起飞按键与输入处理
- Elytra 相关 Mixin 与网络包
- 无限烟花、鞘翅升华等配置项

保留飞行锚转速范围飞行，以及戒指提供的飞行能力。

### 3. Sable 物理结构兼容（可选）

安装 [Sable](https://modrinth.com/mod/sable) 时：

- 飞行锚可在主世界或 Sable plot grid 内生效
- 距离计算支持子层级坐标，适配移动物理结构上的锚点
- 提供 `data/balancedflight/physics_block_properties/flight_anchor.json`（默认 `sable:mass: 4.0`）

未安装 Sable 时行为与原版一致，Sable 为可选依赖。

## 飞行行为修复（2.5.0.3）

- 缓降仅保护正在空中飞行时失去权限的玩家，步行离开锚点范围不再获得缓降；保留其他来源的药水效果。
- 戒指飞行优先于意外鞘翅起飞。按住潜行再触发鞘翅起飞可主动滑翔；可在 `config/balanced_flight.toml` 关闭此偏好，多人游戏请保持客户端和服务端设置一致。
- 死亡、创造及旁观状态不再保留本模组的飞行修饰符。
- 戒指能力要求有效的功能饰品槽，并支持在原槽位替换戒指。
- 锚点摔落保护按实际锚点范围判断；原版飞行免摔落规则保持不变。
- 不添加急迫、不修改挖掘速度；保留原版空中挖掘惩罚。

完整中英更新日志见 `CHANGELOG.md`。

## 环境要求

| 组件 | 版本 |
|------|------|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.228 |
| Create | 6.0.10 |
| Curios API | 9.5.1+1.21.1 |
| GeckoLib | 4.8.4 |
| Sable | 1.2.0+（可选） |

## 构建

需要 Java 21：

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.10"
.\gradlew build
```

产物位于 `build/libs/`。

## 归属与许可

本项目为上游 Create: Balanced Flight 的非官方 fork，MIT 许可。完整版权链见 `LICENSE` 与 `NOTICE`。

部分修改在 AI 辅助下完成，由维护者审核。
