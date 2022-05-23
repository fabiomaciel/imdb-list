// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "IMDB"
    ) {
        MaterialTheme {
            Column {
                Text("Dr Strange")
                Image(
                    painter = painterResource("drstrange.jpg"),
                    contentDescription = "DrStrangeImage"
                )
                Text("Nota: 9 - Ano: 2022")
            }
        }
    }
}
