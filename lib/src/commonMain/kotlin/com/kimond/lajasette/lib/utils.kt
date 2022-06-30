package com.kimond.lajasette

import com.kimond.lajasette.lib.Project
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun loadJase(text: String): Project {
    return Json.decodeFromString(text)
}

fun dumpJase(project: Project): String {
    return Json.encodeToString(project)
}
