package com.lyrieek.dbViewer.view.editor

import com.intellij.codeHighlighting.BackgroundEditorHighlighter
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorLocation
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.fileEditor.impl.LoadTextUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.components.JBLabel
import com.intellij.ui.table.JBTable
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.beans.PropertyChangeListener
import java.util.*
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.table.*

class DBEditor : UserDataHolderBase, FileEditor {
    private val component: JComponent

    constructor(project: Project, file: VirtualFile) {
        val context = LoadTextUtil.loadText(file).toString()
        val label = JBLabel()
        label.foreground = Color.red
        val lines = context.lines()
        val title = lines[0].split(",")
        component = JPanel(GridLayout(0, title.size))
        val tableModel = DefaultTableModel()
        val colModel = DefaultTableColumnModel()
//        title.forEach {
//            colModel.addColumn(it)
//        }
        val table = JBTable(tableModel, colModel)
        for (index in 1 until lines.size) {
            val source = lines[index].split(",")
            val data = Vector<String>()
            source.forEach {
                data.add(it)
            }
            tableModel.addRow(data)
        }
        component.add(table)
//        val bufferedReader = BufferedReader(file?.inputStream?.reader())
//        bufferedReader.use { bufferedReader ->
//            val label = JBLabel(bufferedReader.readText())
//            label.foreground = Color.red
//            component = label
//        }
    }

    override fun dispose() {
    }

    override fun getComponent(): JComponent {
        return component
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return component
    }

    override fun getName(): String {
        return "DB Editor"
    }

    override fun setState(state: FileEditorState) {
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun isValid(): Boolean {
        return true
    }

    override fun selectNotify() {
//        val bufferedReader = BufferedReader(file?.inputStream?.reader())
//        bufferedReader.use { bufferedReader ->
//            val label = JBLabel(bufferedReader.readText())
//            label.foreground = Color.red
//            component = label
//        }
//        TODO("Not yet implemented")
    }

    override fun deselectNotify() {
//        TODO("Not yet implemented")
    }

    override fun addPropertyChangeListener(listener: PropertyChangeListener) {
    }

    override fun removePropertyChangeListener(listener: PropertyChangeListener) {
    }

    override fun getBackgroundHighlighter(): BackgroundEditorHighlighter? {
        return null
    }

    override fun getCurrentLocation(): FileEditorLocation? {
//        return this
        return null
    }

}