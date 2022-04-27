package com.lyrieek.dbViewer.query

import com.lyrieek.dbViewer.BasicDBData
import com.lyrieek.dbViewer.GlobalConfig
import com.lyrieek.dbViewer.connect.ConnectPool

object DataQueryFactory {

    //WARN 这个抽象部分要进行较大展开
    private var data: BasicDBData = EmptyQuery()

    fun basicDBData(): BasicDBData {
        return data
    }

    fun tryConnect() {
        val prop = GlobalConfig.getDBProperties()
        ConnectPool.newConn(prop)
        val url = prop["jdbc.url"].toString()
        if (url.contains("postgre")) {
            data = PostgreQuery()
        } else if (url.contains("oracle")) {
            data = OracleQuery()
        }
    }

    fun stopConnect() {
        ConnectPool.stop()
        data = EmptyQuery()
    }

}