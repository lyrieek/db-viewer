package com.lyrieek.dbViewer.view.editor

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.util.PsiModificationTracker

class DBPsiManager: PsiManager() {
    override fun getProject(): Project {
        TODO("Not yet implemented")
    }

    override fun findFile(file: VirtualFile): PsiFile? {
        TODO("Not yet implemented")
    }

    override fun findViewProvider(file: VirtualFile): FileViewProvider? {
        TODO("Not yet implemented")
    }

    override fun findDirectory(file: VirtualFile): PsiDirectory? {
        TODO("Not yet implemented")
    }

    override fun areElementsEquivalent(element1: PsiElement?, element2: PsiElement?): Boolean {
        TODO("Not yet implemented")
    }

    override fun reloadFromDisk(file: PsiFile) {
        TODO("Not yet implemented")
    }

    override fun addPsiTreeChangeListener(listener: PsiTreeChangeListener) {
        TODO("Not yet implemented")
    }

    override fun addPsiTreeChangeListener(listener: PsiTreeChangeListener, parentDisposable: Disposable) {
        TODO("Not yet implemented")
    }

    override fun removePsiTreeChangeListener(listener: PsiTreeChangeListener) {
        TODO("Not yet implemented")
    }

    override fun getModificationTracker(): PsiModificationTracker {
        TODO("Not yet implemented")
    }

    override fun startBatchFilesProcessingMode() {
        TODO("Not yet implemented")
    }

    override fun finishBatchFilesProcessingMode() {
        TODO("Not yet implemented")
    }

    override fun isDisposed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun dropResolveCaches() {
        TODO("Not yet implemented")
    }

    override fun dropPsiCaches() {
        TODO("Not yet implemented")
    }

    override fun isInProject(element: PsiElement): Boolean {
        TODO("Not yet implemented")
    }
}