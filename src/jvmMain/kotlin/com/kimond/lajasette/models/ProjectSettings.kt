package com.kimond.lajasette.models

import kotlinx.serialization.Serializable

@Serializable
data class ProjectSettings(val emotions: List<String> = emptyList())
