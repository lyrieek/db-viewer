package com.lyrieek.dbViewer.query

import com.fasterxml.jackson.databind.ObjectMapper
import com.intellij.icons.AllIcons
import com.lyrieek.dbViewer.BasicDBData
import com.lyrieek.dbViewer.BasicDBDataType
import com.lyrieek.dbViewer.connect.ConnectPool
import javax.swing.Icon

class PostgreQuery : BasicDBData {
    override val name = "Postgre"
    override val icon: Icon = AllIcons.Providers.Postgresql

    override fun tables(): MutableList<String> {
        ConnectPool.connect.query("select tableName from pg_tables where schemaName='public' order by tableName")
        return ConnectPool.connect.arr()
    }

    override fun views(): MutableList<String> {
        ConnectPool.connect.query("select viewName from pg_views where schemaName='public' order by viewName")
        return ConnectPool.connect.arr()
    }

    override fun functions(): MutableList<String> {
        ConnectPool.connect.query("SELECT routine_name FROM information_schema.routines WHERE routine_schema = 'public' AND routine_type = 'FUNCTION' order by routine_name")
        return ConnectPool.connect.arr()
    }

    override fun procedure(): MutableList<String> {
        ConnectPool.connect.query("SELECT routine_name FROM information_schema.routines WHERE routine_schema = 'public' AND routine_type = 'PROCEDURE' order by routine_name")
        return ConnectPool.connect.arr()
    }

    override fun findObj(type: BasicDBDataType): String {
        when (type) {
            BasicDBDataType.FUNCTION -> {
//                ConnectPool.connect.query("SELECT pg_get_functiondef(oid) FROM pg_proc where PRONAMESPACE=(SELECT oid FROM pg_namespace where nspName = 'public') and prosrc!='aggregate_dummy' and prolang != 13 order by proname")
                ConnectPool.connect.query(
                    "SELECT pg_get_functiondef(oid) FROM\n" +
                            "\tpg_proc where PRONAMESPACE=(SELECT oid FROM pg_namespace where nspName = 'public')" +
                            "\tand prosrc!='aggregate_dummy' and prolang != 13 and PROKIND = 'f' order by proname"
                )
                return ConnectPool.connect.arr().joinToString("\n")
            }
            BasicDBDataType.PROCEDURE -> {
                ConnectPool.connect.query(
                    "SELECT pg_get_functiondef(oid) FROM\n" +
                            "\tpg_proc where PRONAMESPACE=(SELECT oid FROM pg_namespace where nspName = 'public')" +
                            "\tand prosrc!='aggregate_dummy' and prolang != 13 and PROKIND = 'p' order by proname"
                )
                return ConnectPool.connect.arr().joinToString("\n")
            }
            BasicDBDataType.VIEW -> {
                ConnectPool.connect.query("SELECT DEFINITION FROM pg_views where schemaName = 'public'")
                return ConnectPool.connect.arr().joinToString("\n")
            }
            else -> return ""
        }
    }

    override fun showView(name: String): String {
        ConnectPool.connect.query("SELECT * FROM $name")
        val mapper = ObjectMapper()
        val data = ConnectPool.connect.data()
        return mapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(data)
    }
}