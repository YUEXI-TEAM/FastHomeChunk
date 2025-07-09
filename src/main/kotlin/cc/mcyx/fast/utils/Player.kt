package cc.mcyx.fast.utils

import cc.mcyx.fast.config.PlayerConfig
import org.bukkit.entity.Player

fun Player.playerConfig() = PlayerConfig.playerConfigMap[this] ?: PlayerConfig(this)
