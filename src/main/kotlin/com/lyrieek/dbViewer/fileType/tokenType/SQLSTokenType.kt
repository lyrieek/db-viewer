package com.lyrieek.dbViewer.fileType.tokenType

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.lyrieek.dbViewer.fileType.language.SQLSLanguage

interface SQLSTokenType : TokenType {
    companion object {
        val END_OF_LINE_COMMENT: IElementType = IElementType("END_OF_LINE_COMMENT", SQLSLanguage.INSTANCE)
    }
}