package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Laureate(
    val id: String,
    val fullName: String,
    val year: String,
    val category: String,
    val motivation: String,
    val birthCountry: String? = null,
    val birthPlace: String? = null,
    val portraitUrl: String? = null
)
