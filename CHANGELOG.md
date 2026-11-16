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
