package com.kimond.lajasette

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import com.kimond.lajasette.ui.FileDialog
import kotlinx.coroutines.launch

const val APPLICATION_NAME = "La Jasette"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FrameWindowScope.ApplicationMenuBar(state: ApplicationState) = MenuBar {
    val scope = rememberCoroutineScope()

    fun save() = scope.launch { state.save() }
    fun open() = scope.launch { state.open() }

    Menu("File") {
        Item("New", onClick = state::newProject)
        Item(
            "Open...", onClick = { open() }, shortcut = KeyShortcut(key = Key.O, ctrl = true)
        )
        Item(
            "Save",
            onClick = { save() },
            enabled = state.isChanged || state.path == null,
            shortcut = KeyShortcut(key = Key.S, ctrl = true)
        )
    }

}

@Composable
@Preview
fun Application(state: ApplicationState, exit: () -> Unit) {
    val scope = rememberCoroutineScope()


    Window(title = titleOf(state), onCloseRequest = { exit() }) {
        ApplicationMenuBar(state)
        MaterialTheme {
            Button(onClick = {
                state.newProject()
            }) {
                Text(state.project.name)
            }

            Text(state.project.conversations.count().toString())
        }

        if (state.saveDialog.isAwaiting) {
            FileDialog(title = APPLICATION_NAME, isLoad = false, onResult = { state.saveDialog.onResult(it) })
        }

        if (state.openDialog.isAwaiting) {
            FileDialog(title = APPLICATION_NAME, isLoad = true, onResult = { state.openDialog.onResult(it) })
        }
    }
}

private fun titleOf(state: ApplicationState): String {
    val changeMark = if (state.isChanged) "*" else ""
    val filePath = state.path ?: "untitled"
    return "$changeMark$filePath - $APPLICATION_NAME"
}
