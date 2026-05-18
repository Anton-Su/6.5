package domain.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val passwordHash: String,
    val username: String,
    val gender: String,
    val age: Int,
    val favoritePrizes: List<NobelPrize>
)

