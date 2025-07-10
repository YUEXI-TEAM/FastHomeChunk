package cc.mcyx.fast.command

import cc.mcyx.fast.config.LangConfig
import cc.mcyx.fast.service.WorldService
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object MainCommand : BaseCommand(
    "FastHomeChunk",
    "玩家返回自己家园系统",
    "返回家园",
    listOf("fhc"),
    "cc.mcyx.fast.home.chunk"
) {
    override fun execute(p0: CommandSender, p1: String?, p2: Array<out String?>): Boolean {
        if (p0 is Player) {
            if (p2.isEmpty()) {
                p0.sendMessage(LangConfig.lang("错误的参数"))
                return false
            }
            val switch = p2[0] ?: ""
            when (switch) {
                "back" -> {
                    WorldService.spawn(p0.player)
                }

                "quit" -> {
                    WorldService.close(p0.player)
                }
            }
        } else {
            p0.sendMessage(LangConfig.lang("这个命令只有玩家能访问"))
        }
        return true
    }

    override fun tabComplete(sender: CommandSender?, alias: String?, args: Array<out String>): List<String?> {
        return listOf("back", "quit").filter { it.startsWith(args[0].toString().lowercase()) }
    }

}