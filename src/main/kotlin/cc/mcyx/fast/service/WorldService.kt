package cc.mcyx.fast.service

import cc.mcyx.fast.HomeChunk
import cc.mcyx.fast.config.PlayerConfig
import cc.mcyx.fast.config.TemplateConfig
import cc.mcyx.fast.config.TemplateConfig.templateDir
import cc.mcyx.fast.utils.md5
import cc.mcyx.fast.utils.playerConfig
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.entity.Player
import java.io.File

object WorldService {

    /**
     * 创建或者加载家园
     * @param playerConfig 玩家配置
     */
    fun initWorld(playerConfig: PlayerConfig, templateId: String = "default", f: (world: World) -> Unit) {
        ThreadService.submit {
            File(templateDir, templateId).copyRecursively(File(playerConfig.getWorldId()), true)
            Bukkit.getScheduler().runTask(HomeChunk.homeChunk) {
                f.invoke(Bukkit.createWorld(WorldCreator(playerConfig.getWorldId())))
            }
        }
    }


    /**
     * 玩家回到自己家园
     * @param player 玩家
     */
    fun spawn(player: Player) {
        Bukkit.getScheduler().runTask(HomeChunk.homeChunk) {
            initWorld(player.playerConfig()) { world ->
                player.teleport(world.spawnLocation)
                val location = player.location
                world.setSpawnLocation(location.blockX, location.blockY, location.blockZ)
            }
        }
    }

    /**
     * 适应初始化模板
     */
    fun initDefaultTemplate() {
        val defaultTemplate = TemplateConfig.defaultTemplate()
        if (!defaultTemplate.isDirectory) {
            val defaultTemplateWorld = Bukkit.createWorld(WorldCreator(md5(System.currentTimeMillis().toString())))
            Bukkit.unloadWorld(defaultTemplateWorld, true)
            File(defaultTemplateWorld.name).renameTo(defaultTemplate).takeIf { it }.also {
                HomeChunk.homeChunk.logger.info("初始化模板完成")
            }
        }
    }

    /**
     * 卸载一个玩家的世界
     */
    fun unloadWorld(player: Player) {
        getPlayerWorld(player).also { world ->
            Bukkit.unloadWorld(world, true)
        }
    }

    /**
     * 获取玩家的家园世界
     */
    fun getPlayerWorld(player: Player): World? = Bukkit.getWorld(player.playerConfig().getWorldId())

    fun homeIsActivated(player: Player): Boolean {
        return getPlayerWorld(player) != null
    }

    /**
     * 关闭家园世界
     * @param player 玩家
     */
    fun close(player: Player) {
        Bukkit.getWorlds()[0].spawnLocation.also {
            getPlayerWorld(player)?.players?.forEach { player ->
                player.teleport(player)
                player.sendMessage("家园主人已关闭了访问")
            }
            unloadWorld(player)
        }
    }
}