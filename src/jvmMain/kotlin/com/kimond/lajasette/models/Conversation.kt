package com.kimond.lajasette.models

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(val id: String, val chats: List<Chat> = emptyList())
