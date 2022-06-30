package com.kimond.lajasette.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kimond.lajasette.ApplicationState
import com.kimond.lajasette.lib.Conversation

@Composable
@Preview
fun ConversationList(state: ApplicationState) {
    Column(modifier = Modifier.widthIn(max = 250.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { state.addConversation() }) {
                Text("New conversation")
            }
        }
        state.project.conversations.forEach { conversation ->
            ConversationItem(conversation, onClick = { state.selectedConversation = it })
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ConversationItem(conversation: Conversation, onClick: (id: Conversation) -> Unit) =
    ListItem(modifier = Modifier.clickable { onClick(conversation) }, trailing = {
        Text("${conversation.chats.count()}")
    }) {
        Text(conversation.id)
    }
