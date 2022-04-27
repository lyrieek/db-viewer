package com.lyrieek.dbViewer

import com.intellij.openapi.vfs.LocalFileSystem
import com.lyrieek.dbViewer.services.AppSettingsService
import java.io.File
import java.util.*

object GlobalConfig {

    const val GLOBAL_INFO_ID = "DB Viewer"

    fun getDBProperties(): Properties {
        val file = LocalFileSystem.getInstance()
            .findFileByIoFile(File(AppSettingsService.getInstance().state?.configFilePath.toString()))
        val prop = Properties()
        prop.load(file?.inputStream)
        return prop
    }

}