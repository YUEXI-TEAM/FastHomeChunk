package cc.mcyx.fast.command

import org.bukkit.command.Command

abstract class BaseCommand(
    name:
    String,
    desc: String,
    use: String,
    al: List<String>,
    permission: String = "",
    permissionMessage: String = "§4没有权限哦",
) : Command(name, desc, use, al) {
    init {
        if (permission.isNotBlank()) {
            this.permission = permission
            this.permissionMessage = permissionMessage
        }
    }
}