package Routing


import di.AppContainer
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }




//        // public auth route
//        authRoutes()
//
//        // protected prize routes (uses JWT auth plugin)
    // prizeRoutes()
    }
    AppContainer.authController.configure(this)
    AppContainer.prizeController.configure(this)
}