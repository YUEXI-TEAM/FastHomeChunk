package cc.mcyx.fast.config

import cc.mcyx.fast.HomeChunk
import java.io.File

object TemplateConfig {
    val templateDir: File = File(HomeChunk.homeChunk.dataFolder, "templates").also {
        if (!it.isDirectory) it.mkdirs()
    }


    /**
     * 获得一个模板文件夹
     */
    fun getTemplate(templateId: String): File {
        return File(templateDir, templateId)
    }

    /**
     * 模板列表
     */
    fun getTemplates(): List<String> {
        return templateDir.listFiles()!!.filter { it.isDirectory }.map { it.name }
    }

    /**
     * 获取默认模板
     */
    fun defaultTemplate(): File {
        return File(templateDir, "default")
    }
}