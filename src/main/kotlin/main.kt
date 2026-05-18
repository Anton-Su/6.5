package com.example

import Plugins.configureAuthentication
import Plugins.configureCallLogging
import Plugins.configureStatusPages
import Routing.configureRouting
import com.example.Plugins.configureContentNegotiation
import data.Database.DatabaseFactory
import di.appModule
import io.ktor.server.engine.*
import io.ktor.server.application.*
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    appModule()
    configureContentNegotiation()
    configureCallLogging()
    configureStatusPages()
    configureAuthentication()
    configureRouting()
}