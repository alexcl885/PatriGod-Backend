package com.example.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.routing.authRouting
import ktor.routing.monumentosRouting
import ktor.routing.monumentosuserRouting
import java.io.File

fun Application.configureRouting() {
    routing {
        /**
         * Primero realizare las rutas del usuario
         * ya que lo voy a realizar por partes para
         * poder hacerlo poco a poco
         * */
        authRouting()
        monumentosRouting()
        monumentosuserRouting()

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        staticFiles("/images", File("upload/images"))  //para las imágenes
        staticFiles("/files", File("upload/files")) //para otro tipo de ficheros.

    }
}
