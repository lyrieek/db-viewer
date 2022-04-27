package com.lyrieek.dbViewer

import com.lyrieek.dbViewer.connect.DBConnect
import javax.swing.Icon

interface BasicDBData {

    val name: String
    val icon: Icon
    fun tables(): MutableList<String>
    fun views(): MutableList<String>
    fun functions(): MutableList<String>
    fun procedure(): MutableList<String>
    fun findObj(type: BasicDBDataType): String
    fun showView(name: String): String

}