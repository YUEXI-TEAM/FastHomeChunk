package cc.mcyx.fast.utils

import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap

 fun SimpleCommandMap.registerCommand(command: Command) = this.register(command.name, command)
