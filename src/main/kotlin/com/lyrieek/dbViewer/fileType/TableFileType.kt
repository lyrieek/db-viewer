package com.lyrieek.dbViewer.fileType

import com.intellij.openapi.fileTypes.LanguageFileType
import com.lyrieek.dbViewer.fileType.language.DBViewLanguage
import javax.swing.Icon
import javax.swing.ImageIcon

class TableFileType : LanguageFileType(DBViewLanguage()) {

    companion object {
        const val DEFAULT_EXTENSION = "ly-table"
    }

    override fun getName(): String {
        return "表视图"
    }

    override fun getDescription(): String {
        return "表/视图/结果集查看"
    }

    override fun getDefaultExtension(): String {
        return DEFAULT_EXTENSION
    }

    override fun getIcon(): Icon? {
        return ImageIcon(javaClass.getResource("/viewType/table.png"))
    }
}