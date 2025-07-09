package cc.mcyx.fast.config

import cc.mcyx.fast.utils.md5
import org.bukkit.entity.Player

class PlayerConfig(val player: Player) {
    companion object {
        val playerConfigMap = mutableMapOf<Player, PlayerConfig>()
    }

    init {
        playerConfigMap[player] = this
    }

    /**
     * 获取玩家地图名称
     */
    fun getWorldId() = "fhc_${md5(player.uniqueId.toString())}"
}