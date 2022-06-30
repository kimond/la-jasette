package com.kimond.lajasette.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kimond.lajasette.ApplicationState
import com.kimond.lajasette.lib.Chat

@Composable
@Preview
fun ConversationEditor(state: ApplicationState) {
    val selectedConversation = state.project.conversations.find { it.id == state.selectedConversation?.id }
    if (selectedConversation != null) {
        Column {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(selectedConversation.id)
                Button(onClick = { state.addChatToConversation(selectedConversation) }) {
                    Text("Add chat")
                }
            }
            Column {
                selectedConversation.chats.forEach { chat ->
                    ChatItem(chat)
                }
            }
        }
    }
}

@Composable
@Preview
fun ChatItem(chat: Chat) {
    Column {
        Text(chat.id)
        Text(chat.actorId)
        Text(chat.text)
        Text(chat.nextChatId)
    }
}
