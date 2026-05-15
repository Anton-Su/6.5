package com.example

import com.example.api.authRoutes
import com.example.api.prizeRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }


        // public auth route
        authRoutes()

        // protected prize routes (uses JWT auth plugin)
        prizeRoutes()
    }
}