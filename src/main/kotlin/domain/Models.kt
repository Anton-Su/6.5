package com.example.domain

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

@Serializable
data class NobelPrize(
    val year: String,
    val category: String,
    val laureates: List<Laureate> = emptyList()
)

