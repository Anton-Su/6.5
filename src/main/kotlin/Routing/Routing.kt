package Routing


import di.AppContainer
import io.github.smiley4.ktoropenapi.openApi
import io.github.smiley4.ktorredoc.redoc
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
//        route("api.json") {
//            openApi()
//        }
//        // Роут с интерфейсом ReDoc
//        route("docs") {
//            redoc("/api.json") { // указываем на роут выше
//                pageTitle = "My API Docs"
//            }
//        }
//        get("/test") {
//            call.respond(mapOf("message" to "Это открытый маршрут!"))
//        }



//        // public auth route
//        authRoutes()
//
//        // protected prize routes (uses JWT auth plugin)
    // prizeRoutes()
    }
    AppContainer.authController.configure(this)
    AppContainer.prizeController.configure(this)
    AppContainer.userController.configure(this)
}