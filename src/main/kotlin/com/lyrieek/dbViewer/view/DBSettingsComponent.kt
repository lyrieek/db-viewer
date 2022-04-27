package com.lyrieek.dbViewer.view

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBRadioButton
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.ButtonGroup
import javax.swing.JPanel


class DBSettingsComponent {

    var lazyDestroy = false
    var mainPanel: JPanel
    val configFilePath = TextFieldWithBrowseButton()
    val useUpperKeyword = JBCheckBox("使用大写关键字")
    val refreshWhenClose = JBCheckBox("关闭连接后刷新界面")
    val intermingle = JBCheckBox("把函数也当成存储过程")
    val filterLanguageType = JBTextField()
    val filterLanguageList = JBTextField()

    constructor() {
        configFilePath.toolTipText = "e.g. spring.properties"
        filterLanguageList.toolTipText = "用逗号隔开"
        configFilePath.addBrowseFolderListener(TextBrowseFolderListener(FileChooserDescriptor(true, false, true, true, true, false).withShowHiddenFiles(true)))
        mainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("数据库连接properties文件路径: "), configFilePath, 1, false)
            .addComponent(useUpperKeyword, 1)
            .addComponent(refreshWhenClose, 1)
            .addComponent(intermingle, 1)
            .addLabeledComponent("过滤存储过程语言列表", filterLanguageList, 1)
            .addLabeledComponent("过滤方式", filterLanguageType, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

}