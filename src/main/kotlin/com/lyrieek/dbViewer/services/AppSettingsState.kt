package com.lyrieek.dbViewer.services

data class AppSettingsState(
    //通用
    var configFilePath: String = "",
    var useUpperKeyword: Boolean = false,

    //function与procedure的处理
    var filterLanguageType: String = "HIDE",//HIDE:隐藏列表内容,SHOW:仅显示列表内容
    var filterLanguageList: String = "c,java",//以逗号隔开
    var intermingle: Boolean = false,//混杂function与procedure

    //缓存问题
    var refreshWhenClose: Boolean = false
)