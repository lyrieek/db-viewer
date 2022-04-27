package com.lyrieek.dbViewer.query

import com.intellij.icons.AllIcons
import com.lyrieek.dbViewer.BasicDBData
import com.lyrieek.dbViewer.BasicDBDataType
import com.lyrieek.dbViewer.connect.ConnectPool
import javax.swing.Icon

class OracleQuery : BasicDBData {
    override val name = "Oracle"
    override val icon: Icon = AllIcons.Providers.Oracle

    override fun tables(): MutableList<String> {
        ConnectPool.connect.query("select table_name from user_tables order by table_name")
        return ConnectPool.connect.arr()
    }

    override fun views(): MutableList<String> {
        ConnectPool.connect.query("select view_name from user_views order by view_name")
        return ConnectPool.connect.arr()
    }

    override fun functions(): MutableList<String> {
        ConnectPool.connect.query("SELECT P.OBJECT_NAME||'.'||P.PROCEDURE_NAME FROM ALL_PROCEDURES P\n" +
                "LEFT OUTER JOIN USER_ARGUMENTS A ON A.PACKAGE_NAME=P.OBJECT_NAME AND A.OBJECT_NAME=P.PROCEDURE_NAME AND A.ARGUMENT_NAME IS NULL AND A.DATA_LEVEL=0\n" +
                "WHERE P.OWNER=SYS_CONTEXT('USERENV','CURRENT_SCHEMA') \n" +
                "AND P.PROCEDURE_NAME IS NOT NULL AND A.OBJECT_NAME IS NOT NULL\n" +
                "ORDER BY P.OBJECT_NAME,P.PROCEDURE_NAME")
        return ConnectPool.connect.arr()
    }

    override fun procedure(): MutableList<String> {
        ConnectPool.connect.query("\n" +
                "SELECT P.OBJECT_NAME||'.'||P.PROCEDURE_NAME FROM ALL_PROCEDURES P\n" +
                "LEFT OUTER JOIN USER_ARGUMENTS A ON A.PACKAGE_NAME=P.OBJECT_NAME AND A.OBJECT_NAME=P.PROCEDURE_NAME AND A.ARGUMENT_NAME IS NULL AND A.DATA_LEVEL=0\n" +
                "WHERE P.OWNER=SYS_CONTEXT('USERENV','CURRENT_SCHEMA') \n" +
                "AND P.PROCEDURE_NAME IS NOT NULL AND A.OBJECT_NAME IS NULL\n" +
                "ORDER BY P.OBJECT_NAME,P.PROCEDURE_NAME")
        return ConnectPool.connect.arr()
    }

    override fun findObj(type: BasicDBDataType): String {
        when (type) {
            BasicDBDataType.TABLE -> TODO()
            BasicDBDataType.VIEW -> TODO()
            BasicDBDataType.FUNCTION -> TODO()
            BasicDBDataType.EXT_FUNCTION -> TODO()
            BasicDBDataType.PROCEDURE -> TODO()
            else -> return "未实现"
        }
    }

    override fun showView(name: String): String {
        TODO("Not yet implemented")
    }
}