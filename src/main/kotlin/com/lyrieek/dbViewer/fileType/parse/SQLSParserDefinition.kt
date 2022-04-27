package com.lyrieek.dbViewer.fileType.parse

import com.intellij.lang.ASTFactory
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.EmptyLexer
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PlainTextTokenTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiUtilCore
import com.lyrieek.dbViewer.fileType.PsiSQLSFile
import com.lyrieek.dbViewer.fileType.language.SQLSLanguage
import com.lyrieek.dbViewer.fileType.tokenType.SQLSTokenType

class SQLSParserDefinition : ParserDefinition {
    private val file = IFileElementType(SQLSLanguage.INSTANCE)
//    private val elementType: IFileElementType =
//        object : IFileElementType(SQLSFileType.INSTANCE.language) {
//            override fun parseContents(chameleon: ASTNode): ASTNode? {
//                val chars = chameleon.chars
//                return ASTFactory.leaf(PlainTextTokenTypes.PLAIN_TEXT, chars)
//            }
//        }

    override fun createLexer(project: Project?): Lexer {
        return EmptyLexer()
    }

    override fun createParser(project: Project?): PsiParser {
        throw RuntimeException()
    }

    override fun getFileNodeType(): IFileElementType {
        return file
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.create(SQLSTokenType.END_OF_LINE_COMMENT)
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun getWhitespaceTokens(): TokenSet {
        return TokenSet.WHITE_SPACE
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return PsiUtilCore.NULL_PSI_ELEMENT
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return PsiSQLSFile(viewProvider)
    }
}