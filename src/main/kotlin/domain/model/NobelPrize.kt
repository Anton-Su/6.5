package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NobelPrize(
    val id: String,
    val year: String,
    val category: String,
    val laureates: List<Laureate> = emptyList()
)
