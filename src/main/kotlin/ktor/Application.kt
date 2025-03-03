package com.example.ktor

import io.ktor.server.application.*
import ktor.configureContext
import ktor.configureDatabases
import ktor.configureSecurity


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {

    configureContext(this)
    configureSerialization()
    configureSecurity()
    configureRouting()
    configureDatabases()

}
