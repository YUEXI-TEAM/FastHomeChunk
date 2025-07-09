package cc.mcyx.fast.event

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

open class BaseListener : Listener {
    fun register(plugin: JavaPlugin) {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }
}