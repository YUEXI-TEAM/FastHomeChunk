package cc.mcyx.fast.config

import cc.mcyx.fast.HomeChunk
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds

open class BaseConfig(val file: File, autoReload: Boolean = true) {
    val configFile =
        File(HomeChunk.homeChunk.dataFolder, file.path).also {
            if (it.isFile) {
                it.createNewFile()
            }
        }

    open lateinit var config: YamlConfiguration

    fun save() = config.save(configFile)

    fun reload(): YamlConfiguration {
        return YamlConfiguration.loadConfiguration(configFile)
    }


    init {
        this.config = reload()
    }


}