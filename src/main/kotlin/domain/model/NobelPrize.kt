package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NobelPrize(
    val id: Int,
    val year: Int,
    val category: String
)
