# 2.5.0.3 — Minecraft 1.21.1 / NeoForge

- **减少异常缓降。** 仅在正在空中飞行的玩家失去本模组权限、且没有其他飞行权限时提供缓降。步行、普通跳跃、落地、滑翔及死亡不再触发此保护；已有缓降不会被覆盖或因重新获得飞行而清除。
  **Reduced unexpected Slow Falling.** The safeguard now applies only when a player actually loses airborne creative-style flight with no remaining flight permission. Walking, ordinary jumping, landing, gliding, and death do not trigger it. Existing Slow Falling effects are neither overwritten nor cleared when flight is regained.

- **避免戒指飞行被鞘翅意外抢占。** 戒指提供飞行时，默认阻止普通跳跃触发鞘翅起飞；按住潜行可主动滑翔，也可在配置中关闭该保护。已有滑翔、普通鞘翅使用者及其他来源的飞行不受此拦截影响。
  **Prevented accidental Elytra takeoff while using ring flight.** Normal jumping now prioritizes ring flight. Hold Sneak to deliberately start gliding, or disable the new protection setting. Existing gliding, ordinary Elytra users, and flight from other sources are not intercepted by this rule.

- **清理玩家状态切换时的修饰符。** 死亡以及创造/旁观模式下不再授予或保留本模组的飞行修饰符，保留游戏模式原有能力。
  **Cleaned up modifiers across player state changes.** Dead players and players in Creative or Spectator mode no longer receive or retain this mod's flight modifier; game-mode abilities remain intact.

- **修复 Curios 槽位判断。** 禁用槽与装饰槽不提供戒指能力；允许在原槽位替换戒指，同时继续阻止在多个功能槽重复装备。
  **Fixed Curios slot handling.** Inactive and cosmetic slots do not grant ring abilities. Replacing a ring in its current slot is allowed while duplicate functional equipment remains blocked.

- **修正锚点摔落保护范围。** 锚点保护选项按实际有效锚点范围判断，不再将戒指提供的全局飞行误判为“靠近锚点”。原版飞行本身的免摔落规则保持不变。
  **Corrected anchor fall protection scope.** Anchor protection checks actual anchor range instead of treating unrestricted ring flight as proximity to an anchor. Vanilla flight's own fall-immunity rules remain unchanged.

- **新增自动回归测试。** 覆盖缓降触发、鞘翅起飞偏好及创造/旁观/死亡状态的决策边界。
  **Added automated regression tests** for Slow Falling eligibility, Elytra takeoff preferences, and Creative/Spectator/death state boundaries.

**配置说明 / Configuration:** `Prevent Accidental Elytra Takeoff With Ring (sneak to glide)` 默认开启。多人游戏修改此选项时，请在客户端和服务端采用一致设置。
The new takeoff protection setting is enabled by default. Use matching client and server settings when changing it in multiplayer.

**挖掘说明 / Mining:** 本模组不添加急迫、不修改挖掘速度，也不改变原版空中挖掘惩罚。
This mod does not grant Haste, modify mining speed, or change vanilla airborne mining penalties.

# 2.5.0.2 — Minecraft 1.21.1 / NeoForge

- **修复飞行锚跨维度干扰。** 不同维度的同坐标锚点不再互相覆盖，飞行范围仅由玩家所在世界的锚点决定。
  **Fixed cross-dimension Flight Anchor interference.** Anchors at the same coordinates in different dimensions no longer conflict, and flight range checks only use anchors in the player's current world.

- **修复客户端卸载区块导致飞行权限异常丢失的问题。** 锚点登记与飞行权限更新改为仅在服务端执行，并在世界卸载时清理登记。
  **Fixed unexpected loss of anchor flight when client chunks unload.** Anchor registration and flight permission updates now run exclusively on the server, with registry cleanup when worlds unload.

- **改善与其他飞行来源的兼容性。** 使用独立的 NeoForge 创造飞行属性修饰符，移除本模组的飞行能力时不再直接清除其他来源的飞行权限。
  **Improved compatibility with other flight sources.** Flight is now granted through a dedicated NeoForge creative flight attribute modifier, so removing this mod's flight no longer directly clears flight permissions granted by other sources.

- **修复 Sable plot grid 查询的坐标单位。** 使用区块坐标进行判断，修正非主世界中相关区域的识别。
  **Fixed coordinate units in Sable plot grid checks.** Queries now use chunk coordinates to correctly identify plot grid regions outside the Overworld.

- **更新模组封面。** 添加以飞行锚为主体、带有斜向 “fix” 标记的新封面，并将其设为游戏内模组图标。
  **Updated the mod artwork.** Added a new Flight Anchor cover with a diagonal “fix” label and set it as the in-game mod icon.

- **修复构建环境的本机路径依赖。** Gradle Wrapper 改用官方分发地址，并添加 SHA-256 校验。
  **Removed a machine-specific build dependency.** The Gradle Wrapper now uses the official distribution URL with SHA-256 verification.

**玩法说明 / Gameplay note:** 本次未改变失去飞行权限后获得 10 秒缓降的机制；即使当时没有正在飞行，离开锚范围等情况仍可能触发缓降。
The existing 10-second Slow Falling safeguard remains unchanged. Losing flight permission, such as by leaving an anchor's range, can still trigger it even when the player was not actively flying.
