package com.lyrieek.dbViewer

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

object Notify {

    fun info (context: String){
        Notifications.Bus.notify(
            Notification(
                GlobalConfig.GLOBAL_INFO_ID,
                "消息",
                context,
                NotificationType.INFORMATION
            )
        )
    }

    fun error (context: String){
        Notifications.Bus.notify(
            Notification(
                GlobalConfig.GLOBAL_INFO_ID,
                "错误",
                context,
                NotificationType.ERROR
            )
        )
    }
}