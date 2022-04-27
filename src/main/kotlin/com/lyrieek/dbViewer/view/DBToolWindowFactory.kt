package com.lyrieek.dbViewer.view;

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class DBToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val dbToolWindow = DBToolWindow(project)
        dbToolWindow.reader()
        val contentFactory = ContentFactory.SERVICE.getInstance();
        val content = contentFactory.createContent(dbToolWindow.getContent(), "", false);
        toolWindow.contentManager.addContent(content)
    }

}