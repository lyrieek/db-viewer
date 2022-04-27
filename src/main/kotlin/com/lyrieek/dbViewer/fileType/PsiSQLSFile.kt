package com.lyrieek.dbViewer.fileType

import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PlainTextTokenTypes
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.impl.source.PsiFileImpl

class PsiSQLSFile(viewProvider: FileViewProvider) :
    PsiFileImpl(PlainTextTokenTypes.PLAIN_TEXT_FILE, PlainTextTokenTypes.PLAIN_TEXT_FILE, viewProvider) {
    override fun accept(visitor: PsiElementVisitor) {
        visitor.visitFile(this)
    }

    override fun getFileType(): FileType {
        return SQLSFileType()
    }

}