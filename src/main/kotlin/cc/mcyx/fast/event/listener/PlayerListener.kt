package cc.mcyx.fast.event.listener

import cc.mcyx.fast.config.PlayerConfig
import cc.mcyx.fast.event.BaseListener
import cc.mcyx.fast.service.WorldService
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerListener : BaseListener() {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        val playerConfig = PlayerConfig(player)
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        WorldService.unloadWorld(e.player)
    }
}