// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.skia.Image.Companion.makeFromEncoded
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.imageio.ImageIO


fun main() {
    app()
}

fun app() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "IMDB"
    ) {
        MaterialTheme {
            Column {
                Text("Dr Strange")
                Image(
                    bitmap = loadImageBitMap(),
                    contentDescription = "DrStrangeImage",
                    modifier = Modifier.weight(50F)
                )
                Text("Nota: 9 - Ano: 2022")
            }
        }
    }
}

fun get(): InputStream {
    val client = HttpClient(CIO)
    val imageUrl =
        "https://m.media-amazon.com/images/M/MV5BZDJmODA5NGEtZmU1ZC00NDRkLWJkYmEtYmUxYzY0NmY4MTYwXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg"
    return runBlocking {
        client
            .get(imageUrl)
            .body()
    }
}

fun loadImageBitMap(): ImageBitmap {
    val response = get()
    val bufferedImage = ImageIO.read(response)

    val baos = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "jpg", baos)
    return makeFromEncoded(baos.toByteArray()).toComposeImageBitmap()

}
