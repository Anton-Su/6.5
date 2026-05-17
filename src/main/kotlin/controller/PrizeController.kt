package controller



import domain.usecases.GetPrizeUseCase
import domain.model.Laureate
import domain.model.NobelPrize
import io.github.smiley4.ktoropenapi.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable


@Serializable
data class NobelPrizeDto(
    val id: String,
    val year: String,
    val category: String,
    val laureates: List<LaureateDto>
)

@Serializable
data class LaureateDto(
    val id: String,
    val fullName: String,
    val motivation: String,
    val portraitUrl: String?
)

class PrizeController(
    private val getPrizeUseCase: GetPrizeUseCase
) {
    fun configure(application: Application) {
        application.routing {
            authenticate("auth-jwt") {
                get("/prizes") {
                    val prizes = getPrizeUseCase.invoke().filterNotNull()
                    if (prizes.isEmpty()) {
                        call.respond(
                            HttpStatusCode.NotFound,
                            mapOf("error" to "Премии не найдены")
                        )
                        return@get
                    }
                    call.respond(prizes.map { it.toDto() })
                }
                get("/prizes/{year}/{category}") {
                    val year = call.parameters["year"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val prize = getPrizeUseCase.getById(year, category)
                    if (prize == null) {
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Премия не найдена"))
                        return@get
                    }
                    call.respond(prize.toDto())
                }

                get("/prizes/{year}/{category}/laureates") {
                    val year = call.parameters["year"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val laureates = getPrizeUseCase.getById(year, category)?.laureates
                    if (laureates.isNullOrEmpty()) {
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Лауреаты не найдены"))
                        return@get
                    }
                    call.respond(laureates.map { it.toDto() })
                }


//                get("/users/me/countries", {
//                    tags = listOf("Users", "Countries")
//                    summary = "Список посещенных стран пользователя"
//                    securitySchemeNames = listOf("MyJwtAuth")
//                    response {
//                        HttpStatusCode.OK to {
//                            description = "Список стран"
//                            body<List<CountryResponseDto>>()
//                        }
//                        HttpStatusCode.InternalServerError to {
//                            description = "Ошибка сервера"
//                        }
//                    }
//                }) {
//                    val principal = call.principal<JWTPrincipal>()
//                    val username = principal!!.payload.getClaim("username").asString()
//                    val user = getUserUseCase(username)
//
//                    if (user == null) {
//                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Пользователь не найден"))
//                        return@get
//                    }
//
//                    val countriesResult = getUserCountriesUseCase(user.id)
//
//                    countriesResult.fold(
//                        onSuccess = { countries ->
//                            val dtos = countries.map { country ->
//                                CountryResponseDto(
//                                    id = country.id,
//                                    name = country.name,
//                                    code = country.code,
//                                    visitedAt = country.visitedAt
//                                )
//                            }
//                            call.respond(HttpStatusCode.OK, dtos)
//                        },
//                        onFailure = { e ->
//                            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to (e.message ?: "Неизвестная ошибка")))
//                        }
//                    )
//                }

            }

        }
    }
}

fun NobelPrize.toDto(): NobelPrizeDto = NobelPrizeDto(
    id = id,
    year = year,
    category = category,
    laureates = laureates.map { it.toDto() }
)

fun Laureate.toDto(): LaureateDto = LaureateDto(
    id = id,
    fullName = fullName,
    motivation = motivation,
    portraitUrl = portraitUrl
)

