package com.lyrieek.dbViewer.fileType

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import com.lyrieek.dbViewer.fileType.language.SQLSLanguage
import javax.swing.Icon

class SQLSFileType : LanguageFileType(SQLSLanguage.INSTANCE) {
    companion object {
        val INSTANCE: SQLSFileType = SQLSFileType()
    }

    override fun getName(): String {
        return "BATCH SQL"
    }

    override fun getDescription(): String {
        return "大批量SQL语句"
    }

    override fun getDefaultExtension(): String {
        return "sqlS"
    }

    override fun getIcon(): Icon {
        return AllIcons.Webreferences.Server
    }

}