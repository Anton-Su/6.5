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
    val id: Int,
    val year: Int,
    val category: String,
    val laureates: List<LaureateDto> = emptyList()
)

@Serializable
data class LaureateDto(
    val id: Int,
    val fullName: String,
    val motivation: String,
    val portion: String,
    val portraitUrl: String?
)

class PrizeController(
    private val getPrizeUseCase: GetPrizeUseCase
) {
    fun configure(application: Application) {
        application.routing {
            get("/prizes") {
                val prizes = getPrizeUseCase.invoke()
                if (prizes.isEmpty()) {
                    call.respond(
                        HttpStatusCode.NotFound,
                        mapOf("error" to "Премии не найдены")
                    )
                    return@get
                }
                val prizesWithLaureates = prizes.map { prize ->
                    val laureates = getPrizeUseCase.getLaureates(prize.year, prize.category)
                    prize.toDto(laureates)
                }
                call.respond(prizesWithLaureates)
            }
            authenticate("auth-jwt") {
                get("/prizes/{year}/{category}") {
                    val year = call.parameters["year"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val prize = getPrizeUseCase.getById(year, category)
                    if (prize == null) {
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Премия не найдена"))
                        return@get
                    }
                    val laureates = getPrizeUseCase.getLaureates(year, category)
                    call.respond(prize.toDto(laureates))
                }
                get("/prizes/{year}/{category}/laureates") {
                    val year = call.parameters["year"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val category = call.parameters["category"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                    val laureates = getPrizeUseCase.getLaureates(year, category)
                    if (laureates.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to "Лауреаты не найдены"))
                        return@get
                    }
                    call.respond(laureates.map { it.toDto() })
                }
            }
        }
    }
}

fun NobelPrize.toDto(laureates: List<Laureate> = emptyList()): NobelPrizeDto = NobelPrizeDto(
    id = id,
    year = year,
    category = category,
    laureates = laureates.map { it.toDto() }
)

fun Laureate.toDto(): LaureateDto = LaureateDto(
    id = id,
    fullName = fullName,
    portion = portion,
    motivation = motivation,
    portraitUrl = portraitUrl
)

