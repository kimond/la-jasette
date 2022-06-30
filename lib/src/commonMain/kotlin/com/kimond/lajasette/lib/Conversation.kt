package com.kimond.lajasette.lib

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(val id: String, val chats: List<Chat> = emptyList())
