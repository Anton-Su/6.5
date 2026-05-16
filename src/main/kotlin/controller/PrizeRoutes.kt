package com.example.api

//import com.example.data.InMemoryPrizeRepository
//import com.example.data.PrizeRepository
//import io.ktor.http.*
//import io.ktor.server.auth.*
//import io.ktor.server.response.*
//import io.ktor.server.routing.*
//
//fun Route.prizeRoutes(repo: PrizeRepository) {
//    authenticate("auth-jwt") {
//        route("/prizes") {
//            get {
//                call.respond(repo.getAll())
//            }
//            get("/{year}/{category}") {
//                val year = call.parameters["year"] ?: return@get call.respond(HttpStatusCode.BadRequest)
//                val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
//                val prize = repo.find(year, category)
//                if (prize == null) call.respond(HttpStatusCode.NotFound)
//                else call.respond(prize)
//            }
//            get("/{year}/{category}/laureates") {
//                val year = call.parameters["year"] ?: return@get call.respond(HttpStatusCode.BadRequest)
//                val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
//                val laureates = repo.findLaureates(year, category)
//                if (laureates == null) call.respond(HttpStatusCode.NotFound)
//                else call.respond(laureates)
//            }
//        }
//    }
//}
//
//// Helper to register routes with a default in-memory repo
//fun Route.prizeRoutes() = prizeRoutes(InMemoryPrizeRepository())
//
