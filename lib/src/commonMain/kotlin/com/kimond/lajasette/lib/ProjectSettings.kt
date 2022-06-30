package com.kimond.lajasette.lib

import kotlinx.serialization.Serializable

@Serializable
data class ProjectSettings(val emotions: List<String> = emptyList())
