package cc.mcyx.fast

import cc.mcyx.fast.command.MainCommand
import cc.mcyx.fast.event.BaseListener
import cc.mcyx.fast.service.WorldService
import cc.mcyx.fast.utils.registerCommand
import org.bukkit.Bukkit
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.java.JavaPlugin
import java.util.jar.JarFile
import kotlin.reflect.full.superclasses

class HomeChunk : JavaPlugin() {

    companion object {
        lateinit var homeChunk: HomeChunk
    }

    override fun onLoad() {
        homeChunk = this
    }

    override fun onEnable() {
        initListener()
        initCommand()
        WorldService.initDefaultTemplate()
    }

    private fun initCommand() = (server.javaClass.getDeclaredField("commandMap")
        .also { it.isAccessible = true }
        .get(server) as SimpleCommandMap)
        .registerCommand(MainCommand)

    private fun initListener() = JarFile(HomeChunk::class.java.protectionDomain.codeSource.location.path).entries()
        .toList()
        .filter {
            !it.isDirectory && it.name.contains("cc/mcyx")
        }.map {
            Class.forName(it.name.replace("/", ".").replace(".class", "")).kotlin
        }.filter { clazz ->
            clazz.superclasses.contains(BaseListener::class)
        }.forEach { clazz ->
            Bukkit.getPluginManager().registerEvents(clazz.objectInstance as BaseListener, this)
        }
}

