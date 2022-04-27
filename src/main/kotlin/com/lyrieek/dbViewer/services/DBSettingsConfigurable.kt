package com.lyrieek.dbViewer.services

import com.intellij.openapi.options.Configurable
import com.lyrieek.dbViewer.view.DBSettingsComponent
import javax.swing.JComponent

class DBSettingsConfigurable : Configurable {
    private var settingsComponent = DBSettingsComponent()

    override fun getDisplayName(): String {
        return "DB Viewer"
    }

    override fun createComponent(): JComponent? {
        if (settingsComponent.lazyDestroy) {
            settingsComponent = DBSettingsComponent()
        }
        return settingsComponent.mainPanel
    }

    override fun isModified(): Boolean {
        val settings: AppSettingsState = AppSettingsService.getInstance().state!!
        var modified: Boolean = settingsComponent.configFilePath.text != settings.configFilePath
        modified = modified or (settingsComponent.useUpperKeyword.isSelected != settings.useUpperKeyword)
        modified = modified or (settingsComponent.filterLanguageType.text != settings.filterLanguageType)
        modified = modified or (settingsComponent.refreshWhenClose.isSelected != settings.refreshWhenClose)
        return modified
    }

    override fun apply() {
        val settings: AppSettingsState = AppSettingsService.getInstance().state!!
        settings.configFilePath = settingsComponent.configFilePath.text
        settings.useUpperKeyword = settingsComponent.useUpperKeyword.isSelected
        settings.refreshWhenClose = settingsComponent.refreshWhenClose.isSelected
        settings.filterLanguageList = settingsComponent.filterLanguageList.text
        settings.filterLanguageType = settingsComponent.filterLanguageType.text
    }

    override fun reset() {
        val settings: AppSettingsState = AppSettingsService.getInstance().state!!
        settingsComponent.configFilePath.text = settings.configFilePath
        settingsComponent.useUpperKeyword.isSelected = settings.useUpperKeyword
        settingsComponent.refreshWhenClose.isSelected = settings.refreshWhenClose
        settingsComponent.filterLanguageList.text = settings.filterLanguageList
        settingsComponent.filterLanguageType.text = settings.filterLanguageType
    }

    override fun disposeUIResources() {
        settingsComponent.lazyDestroy = true
    }

}