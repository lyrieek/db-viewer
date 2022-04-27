package com.lyrieek.dbViewer.connect;

import java.sql.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class DBConnect constructor(url: String, user: String, password: String) : AutoCloseable {
    private val conn: Connection
    private lateinit var stmt: PreparedStatement
    private lateinit var rs: ResultSet

    init {
        try {
            conn = DriverManager.getConnection(url, user, password)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun query(sql: String): ResultSet {
        try {
            createStmtSQL(sql)
            this.rs = stmt.executeQuery()
            return rs
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun query(sql: String, param: String): ResultSet {
        try {
            createStmtSQL(sql)
            stmt.setString(1, param)
            this.rs = stmt.executeQuery()
            return rs
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun query(sql: String, param1: String, param2: String): ResultSet {
        try {
            createStmtSQL(sql)
            stmt.setString(1, param1)
            stmt.setString(2, param2)
            this.rs = stmt.executeQuery()
            return rs
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun exec(sql: String, param: String): Int {
        try {
            createStmtSQL(sql)
            stmt.setString(1, param)
            return stmt.executeUpdate()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    private fun createStmtSQL(sql: String) {
        try {
            this.stmt = conn.prepareStatement(sql)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun data(): MutableList<HashMap<String, String>> {
        try {
            var data: MutableList<HashMap<String, String>> = ArrayList()
            var metaData: ResultSetMetaData = rs.metaData
            var columnCount: Int = metaData.columnCount
            while (rs.next()) {
                var map: HashMap<String, String> = HashMap()
                for (i in 1..columnCount) {
                    when (metaData.getColumnType(i)) {
                        Types.INTEGER -> {
                            map[metaData.getColumnLabel(i)] = rs.getInt(i).toString()
                        }
                        else -> {
                            var value = rs.getString(i)
                            if (value == null) value = ""
                            map[metaData.getColumnLabel(i)] = value
                        }
                    }
                }
                data.add(map)
            }
            return data
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun arr(): MutableList<String> {
        try {
            var data: MutableList<String> = ArrayList()
            while (rs.next()) {
                data.add(rs.getString(1))
            }
            return data
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun single(): String {
        try {
            rs.next()
            return rs.getString(1)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Throws(Exception::class)
    override fun close() {
        if (!rs.isClosed) {
            rs.close()
        }
        if (!stmt.isClosed) {
            stmt.close()
        }
        conn.close()
    }

}
