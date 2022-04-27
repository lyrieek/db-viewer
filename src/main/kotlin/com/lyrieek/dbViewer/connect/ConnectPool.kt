package com.lyrieek.dbViewer.connect

import java.util.Properties

object ConnectPool {
    lateinit var connect: DBConnect

    fun newConn(prop: Properties) {
        Class.forName(prop["jdbc.driverClassName"].toString())
        connect = DBConnect(
            prop["jdbc.url"]?.toString()!!,
            prop["jdbc.username"]?.toString()!!, prop["jdbc.password"]?.toString()!!
        )
    }

    fun stop() {
        connect.close()
    }
}
