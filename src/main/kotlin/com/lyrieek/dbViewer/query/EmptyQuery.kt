package com.lyrieek.dbViewer.query

import com.intellij.icons.AllIcons
import com.lyrieek.dbViewer.BasicDBData
import com.lyrieek.dbViewer.BasicDBDataType
import java.util.Collections
import javax.swing.Icon

class EmptyQuery : BasicDBData {
    override val name = "未连接"
    override val icon: Icon = AllIcons.Nodes.UnknownJdk

    override fun tables(): MutableList<String> {
        return mutableListOf("尚未加载")
    }

    override fun views(): MutableList<String> {
        return mutableListOf("尚未加载")
    }

    override fun functions(): MutableList<String> {
        return mutableListOf("尚未加载")
    }

    override fun procedure(): MutableList<String> {
        return mutableListOf("尚未加载")
    }

    override fun findObj(type: BasicDBDataType): String {
        return ""
    }

    override fun showView(name: String): String {
        return ""
    }
}