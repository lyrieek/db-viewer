package com.lyrieek.dbViewer.view

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextField
import com.intellij.ui.components.panels.Wrapper
import com.intellij.ui.treeStructure.Tree
import com.lyrieek.dbViewer.BasicDBDataType
import com.lyrieek.dbViewer.GlobalConfig
import com.lyrieek.dbViewer.Notify
import com.lyrieek.dbViewer.connect.DBConnect
import com.lyrieek.dbViewer.query.DataQueryFactory
import com.lyrieek.dbViewer.query.EmptyQuery
import com.lyrieek.dbViewer.services.AppSettingsService
import java.awt.BorderLayout
import java.awt.event.*
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.tree.DefaultMutableTreeNode


class DBToolWindow(private val project: Project) {

    private val dbToolWindowContent: JBPanel<DBToolWindowPanel> =
        JBPanel<DBToolWindowPanel>(BorderLayout())

    private val searchBox = JBTextField()

    private var searchBoxDisplay = false

    fun reader() {
        dbToolWindowContent.removeAll()
        val nodes = DefaultMutableTreeNode("db")
        val basicDBData = DataQueryFactory.basicDBData()
        nodes.add(loadNode(BasicDBDataType.TABLE.name, basicDBData.tables()))
        nodes.add(loadNode(BasicDBDataType.VIEW.name, basicDBData.views()))
        nodes.add(loadNode(BasicDBDataType.FUNCTION.name, basicDBData.functions()))
        nodes.add(loadNode(BasicDBDataType.PROCEDURE.name, basicDBData.procedure()))
        val tree = Tree(nodes)
        tree.isRootVisible = false
        val fileFactory = PsiFileFactory.getInstance(project)

        tree.addMouseListener(object : MouseAdapter() {
            override fun mouseReleased(e: MouseEvent) {
                if (e.isPopupTrigger && tree.selectionPath != null && tree.selectionPath.pathCount >= 2) {
                    if (DataQueryFactory.basicDBData() is EmptyQuery) {
                        return
                    }
                    val menu = JPopupMenu()
                    val item = JMenuItem("查看")
                    item.addMouseListener(object : MouseAdapter() {
                        override fun mouseReleased(e: MouseEvent) {
                            val selectText = tree.selectionPath.getPathComponent(1).toString()
                            val type = BasicDBDataType.valueOf(selectText)
                            val content = if (type == BasicDBDataType.TABLE && tree.selectionPath.pathCount == 3) {
                                val name = tree.selectionPath.getPathComponent(2).toString()
                                basicDBData.showView(name)
                            } else {
                                basicDBData.findObj(type)
                            }
                            if (content.isEmpty()) {
                                return
                            }
                            val psiFile = fileFactory.createFileFromText(
                                selectText,
                                type.fileType,
                                content,
                                System.currentTimeMillis(),
                                true
                            )
                            val virtualFile = psiFile.virtualFile
                            OpenFileDescriptor(project, virtualFile).navigate(true)
                        }
                    })
                    menu.add(item)
                    menu.add(JMenuItem("编辑"))
                    if (tree.selectionPath.pathCount >= 3) {
                        menu.add(JMenuItem("删除"))
                    }
                    menu.add(JMenuItem("导出SQL语句"))
                    menu.show(tree, e.x, e.y)
                }
            }
        })
        tree.addTreeSelectionListener {
            if (it.path.pathCount == 2) {
//                for (fileLanguage in Language.getRegisteredLanguages()) {
//                    println(fileLanguage.javaClass.toString()+":"+fileLanguage)
//                }
//                val editorFactory = EditorFactory.getInstance()
//                editorFactory.createEditor(editorFactory.createDocument("select * from " + it.path.getPathComponent(2)))
            } else if (it.path.pathCount == 3) {
//                PsiFileFactory.getInstance(project)
//                    .createFileFromText(SQLLanguage(), "select * from " + it.path.getPathComponent(1))
            }
//            Notify.info(it.path.toString());
//            FileDocumentManager.getInstance().
        }
        val wrapper = Wrapper()
        val actions = DefaultActionGroup()
        actions.addAction(RefreshBtn())
        actions.addAction(TestConnectAction())
        actions.addAction(object : AnAction(DataQueryFactory.basicDBData().icon) {
            override fun actionPerformed(e: AnActionEvent) {
                Notify.info(
                    DataQueryFactory.basicDBData().name + "\n" + (if (DataQueryFactory.basicDBData() is EmptyQuery) "连接已经断开" else "正在连接中")
                )
            }
        })
        if (DataQueryFactory.basicDBData() is EmptyQuery) {
            actions.addAction(object : AnAction(AllIcons.Actions.StartMemoryProfile) {
                override fun actionPerformed(e: AnActionEvent) {
                    try {
                        DataQueryFactory.tryConnect()
                    } catch (ex: Exception) {
                        Notify.error("无法连接:" + ex.message)
                    }
                    reader()
                }
            })
        } else {
            actions.addAction(object : AnAction(AllIcons.Actions.Suspend) {
                override fun actionPerformed(e: AnActionEvent) {
                    DataQueryFactory.stopConnect()
                    if (AppSettingsService.getInstance().state?.refreshWhenClose == true) {
                        reader()
                    }
                }
            })
        }
        actions.addAction(SelectCfgFileAction())
        actions.addSeparator()
        actions.addAction(object : AnAction(AllIcons.Actions.Search) {
            override fun actionPerformed(e: AnActionEvent) {
                searchBoxDisplay = !searchBoxDisplay
                reader()
            }
        })
        val actionToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLBAR, actions, true)
        val searchWrapper = Wrapper()

        searchBox.toolTipText = "搜索"
        searchBox.isVisible = searchBoxDisplay
        searchBox.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ENTER) {
                    reader()
                }
            }
        })
        searchWrapper.add(searchBox, BorderLayout.NORTH)
        searchWrapper.add(JBScrollPane(tree), BorderLayout.CENTER)
        wrapper.add(actionToolbar.component, BorderLayout.NORTH)
//        wrapper.add(JBScrollPane(tree), BorderLayout.CENTER)
        wrapper.add(searchWrapper, BorderLayout.CENTER)
        dbToolWindowContent.add(wrapper)
    }

    private fun loadNode(root: String, list: List<String>): DefaultMutableTreeNode {
        val node = DefaultMutableTreeNode(root)
        list.forEach {
            if (searchBox.text.isNotBlank() && !it.contains(searchBox.text)) {
                return@forEach
            }
            node.add(DefaultMutableTreeNode(it))
        }
        return node
    }

    fun getContent(): JBPanel<DBToolWindowPanel> {
        return dbToolWindowContent
    }

    inner class RefreshBtn : AnAction(AllIcons.Actions.Refresh) {
        override fun actionPerformed(e: AnActionEvent) {
            reader()
        }
    }

    inner class TestConnectAction : AnAction(AllIcons.Nodes.Test) {
        override fun actionPerformed(e: AnActionEvent) {
            try {
                val prop = GlobalConfig.getDBProperties()
                Class.forName(prop["jdbc.driverClassName"].toString())
                DBConnect(
                    prop["jdbc.url"]?.toString()!!,
                    prop["jdbc.username"]?.toString()!!,
                    prop["jdbc.password"]?.toString()!!
                ).use { conn ->
                    conn.query("SELECT 'successful' from dual")
                    Notify.info(conn.single())
                }
            } catch (e: Exception) {
                println(e.stackTrace.joinToString("\n"))
                e.printStackTrace()
            }
        }
    }

    inner class SelectCfgFileAction : AnAction(AllIcons.General.Settings) {
        override fun actionPerformed(e: AnActionEvent) {
            reader()
        }
    }

//    inner class StartAction : AnAction(AllIcons.Actions.StartMemoryProfile) {
//        override fun actionPerformed(e: AnActionEvent) {
//            DataQueryFactory.tryConnect()
//        }
//    }

}
