package com.kimond.lajasette

import androidx.compose.runtime.*
import com.kimond.lajasette.lib.Chat
import com.kimond.lajasette.lib.Conversation
import com.kimond.lajasette.lib.Project
import com.kimond.lajasette.lib.ProjectSettings
import kotlinx.coroutines.CompletableDeferred
import java.nio.file.Path
import kotlin.io.path.readText

@Composable
fun rememberApplicationState() = remember {
    ApplicationState()
}

class ApplicationState {
    var path: Path? by mutableStateOf(null)
        private set
    var isChanged by mutableStateOf(false)
        private set
    var selectedConversation: Conversation? by mutableStateOf(null)

    var project: Project by mutableStateOf(
        Project(
            "New Project", ProjectSettings(listOf("happy", "angry")), listOf(Conversation("prison-intro"))
        )
    )
        private set

    val openDialog = DialogState<Path?>()
    val saveDialog = DialogState<Path?>()
    val exitDialog = DialogState<Boolean>()

    fun newProject() {
        project = Project("New Project")
    }

    suspend fun save() {
        if (path == null) {
            val path = saveDialog.awaitResult()
            if (path != null) {
                save(path)
            }
        }
    }

    private suspend fun save(path: Path) {
        isChanged = false
        this.path = path

        val text = dumpJase(project)
        path.toFile().writeText(text)

    }

    suspend fun open() {
        val path = openDialog.awaitResult()
        if (path != null) {
            open(path)
        }

    }

    private suspend fun open(path: Path) {
        isChanged = false
        this.path = path

        val loadedText = path.readText()
        this.project = loadJase(loadedText)
    }

    fun addConversation() {
        project =
            project.copy(conversations = project.conversations + Conversation("Conversation ${project.conversations.count() + 1}"))
    }

    fun addChatToConversation(conversation: Conversation) {
        project = project.copy(conversations = project.conversations.map {
            if (it.id == conversation.id) {
                it.copy(chats = it.chats + Chat("???"))
            } else {
                it
            }
        })
    }
}

class DialogState<T> {
    private var onResult: CompletableDeferred<T>? by mutableStateOf(null)

    val isAwaiting get() = onResult != null

    suspend fun awaitResult(): T {
        onResult = CompletableDeferred()
        val result = onResult!!.await()
        onResult = null
        return result
    }

    fun onResult(result: T) = onResult!!.complete(result)
}
