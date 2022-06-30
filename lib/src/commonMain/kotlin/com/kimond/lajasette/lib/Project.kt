package com.kimond.lajasette.lib

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val name: String = "New project",
    val settings: ProjectSettings = ProjectSettings(emptyList()),
    val conversations: List<Conversation> = emptyList()
)


