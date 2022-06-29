// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.window.*
import com.kimond.lajasette.Application
import com.kimond.lajasette.rememberApplicationState


fun main() = application {
    Application(rememberApplicationState(), ::exitApplication)
}
