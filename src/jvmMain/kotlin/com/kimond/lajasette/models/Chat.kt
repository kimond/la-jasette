package com.kimond.lajasette.models

import kotlinx.serialization.Serializable

@Serializable
data class Chat(val id: String, val actorId: String, val emotion: String, val text: String, val nextChatId: String)
