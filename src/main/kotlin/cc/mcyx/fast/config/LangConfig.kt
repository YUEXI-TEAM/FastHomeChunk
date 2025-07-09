package cc.mcyx.fast.config

import java.io.File

object LangConfig : BaseConfig(File("lang.yml")) {

    fun lang(i18n: String): String {
        if (config.contains(i18n)) return config.getString(i18n, i18n)
        config.set(i18n, i18n).also { save() }
        return i18n
    }
}