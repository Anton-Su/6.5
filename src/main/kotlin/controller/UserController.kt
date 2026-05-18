package controller

import domain.usecases.AddFavoritePrizeUseCase
import domain.usecases.GetCurrentUserUseCase
import domain.usecases.GetUserFavoritesUseCase
import domain.usecases.GetPrizeUseCase
import domain.usecases.RemoveFavoritePrizeUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    val id: Int,
    val username: String,
    val gender: String,
    val age: Int,
    val favoritePrizes: List<NobelPrizeDto>
)

class UserController(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getUserFavoritesUseCase: GetUserFavoritesUseCase,
    private val addFavoritePrizeUseCase: AddFavoritePrizeUseCase,
    private val removeFavoritePrizeUseCase: RemoveFavoritePrizeUseCase,
    private val getPrizeUseCase: GetPrizeUseCase
) {
    fun configure(application: Application) {
        application.routing {
            authenticate("auth-jwt") {
                get("/users/me") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal?.payload?.getClaim("username")?.asString()
                        ?: return@get call.respond(HttpStatusCode.Unauthorized)

                    val user = getCurrentUserUseCase(username)
                    if (user == null) {
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Пользователь не найден"))
                        return@get
                    }

                    val favorites = getUserFavoritesUseCase(user.id)
                    val favoritesWithLaureates = favorites.map { prize ->
                        val laureates = getPrizeUseCase.getLaureates(prize.year, prize.category)
                        prize.toDto(laureates)
                    }
                    call.respond(UserDto(
                        id = user.id,
                        username = user.username,
                        gender = user.gender,
                        age = user.age,
                        favoritePrizes = favoritesWithLaureates
                    ))
                }

                get("/users/me/prizes") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal?.payload?.getClaim("username")?.asString()
                        ?: return@get call.respond(HttpStatusCode.Unauthorized)

                    val user = getCurrentUserUseCase(username) ?: return@get call.respond(HttpStatusCode.NotFound, mapOf("error" to "Пользователь не найден"))
                    val favorites = getUserFavoritesUseCase(user.id)
                    val favoritesWithLaureates = favorites.map { prize ->
                        val laureates = getPrizeUseCase.getLaureates(prize.year, prize.category)
                        prize.toDto(laureates)
                    }
                    print(favoritesWithLaureates)
                    call.respond(favoritesWithLaureates)
                }

                post("/users/me/prizes/{prizeId}") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal?.payload?.getClaim("username")?.asString()
                        ?: return@post call.respond(HttpStatusCode.Unauthorized)

                    val prizeId = call.parameters["prizeId"]?.toIntOrNull() ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val user = getCurrentUserUseCase(username) ?: return@post call.respond(HttpStatusCode.NotFound, mapOf("error" to "Пользователь не найден"))

                    val ok = addFavoritePrizeUseCase(user.id, prizeId)
                    if (ok) call.respond(HttpStatusCode.OK, mapOf("result" to "added")) else call.respond(HttpStatusCode.NotFound, mapOf("error" to "Премия не найдена или не может быть добавлена"))
                }

                delete("/users/me/prizes/{prizeId}") {
                    val principal = call.principal<JWTPrincipal>()
                    val username = principal?.payload?.getClaim("username")?.asString()
                        ?: return@delete call.respond(HttpStatusCode.Unauthorized)

                    val prizeId = call.parameters["prizeId"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                    val user = getCurrentUserUseCase(username) ?: return@delete call.respond(HttpStatusCode.NotFound, mapOf("error" to "Пользователь не найден"))

                    val ok = removeFavoritePrizeUseCase(user.id, prizeId)
                    if (ok) call.respond(HttpStatusCode.OK, mapOf("result" to "removed")) else call.respond(HttpStatusCode.NotFound, mapOf("error" to "Премия не найдена у пользователя"))
                }
            }
        }
    }
}


