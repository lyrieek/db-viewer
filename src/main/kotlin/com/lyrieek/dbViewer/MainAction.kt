package com.lyrieek.dbViewer

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class MainAction() : AnAction("db-viewer") {

    override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.project
        Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon())
    }

}