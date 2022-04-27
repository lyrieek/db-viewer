package com.lyrieek.dbViewer.services

import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "com.lyrieek.services.AppSettingsState",
    storages = [Storage("db-viewer.xml")]
)
class AppSettingsService : PersistentStateComponent<AppSettingsState> {

    private val state = AppSettingsState()

    companion object {
        @JvmStatic
        fun getInstance() : PersistentStateComponent<AppSettingsState> {
            return ServiceManager.getService(AppSettingsService::class.java)
        }
    }

    override fun getState(): AppSettingsState {
        return state
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this.state)
    }

}
