package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Laureate(
    val id: Int,
    val prizeId: Int,
    val fullName: String,
    val motivation: String,
    val portion: String,
    val portraitUrl: String? = null
)
