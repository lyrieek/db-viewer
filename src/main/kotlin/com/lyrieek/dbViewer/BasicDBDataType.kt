package com.lyrieek.dbViewer

import com.intellij.json.JsonFileType
import com.intellij.openapi.fileTypes.FileType
import com.lyrieek.dbViewer.fileType.SQLSFileType

enum class BasicDBDataType {
    TABLE("表", JsonFileType.INSTANCE),
    VIEW("视图"),
    FUNCTION("函数"),
    EXT_FUNCTION("扩展函数"),
    PROCEDURE("存储过程");

    val viewName: String
    val fileType: FileType

    constructor(viewName: String) {
        this.viewName = viewName
        this.fileType = SQLSFileType.INSTANCE
    }

    constructor(viewName: String, type: FileType) {
        this.viewName = viewName
        this.fileType = type
    }

}