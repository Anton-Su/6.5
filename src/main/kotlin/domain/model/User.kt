package domain.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val username: String,
    val gender: String,
    val age: String,
    val favoritePrizes: List<NobelPrize>
)

