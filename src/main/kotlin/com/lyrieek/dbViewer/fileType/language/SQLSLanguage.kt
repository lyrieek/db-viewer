package com.lyrieek.dbViewer.fileType.language

import com.intellij.lang.Language

class SQLSLanguage : Language("SQLs", "text/plain") {
    companion object {
        val INSTANCE: SQLSLanguage = SQLSLanguage()
    }

    override fun getDisplayName(): String {
        return "Batch SQL"
    }
}