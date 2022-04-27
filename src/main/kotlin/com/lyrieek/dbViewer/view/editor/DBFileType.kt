package com.lyrieek.dbViewer.view.editor

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory
import com.lyrieek.dbViewer.fileType.TableFileType

class DBFileType: FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(TableFileType());
    }
}