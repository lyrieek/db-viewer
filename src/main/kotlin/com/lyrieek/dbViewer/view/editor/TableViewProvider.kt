package com.lyrieek.dbViewer.view.editor

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.Project
import com.lyrieek.dbViewer.fileType.TableFileType

class TableViewProvider : FileEditorProvider {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.extension == TableFileType.DEFAULT_EXTENSION;
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return DBEditor(project, file)
    }

    override fun getEditorTypeId(): String {
        return "app.editor";
    }

    override fun getPolicy(): FileEditorPolicy {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}