package domain.repository

import domain.model.Laureate
import domain.model.NobelPrize

interface PrizeRepository {
    suspend fun getAll(): List<NobelPrize>
    suspend fun findPrize(year: Int, category: String): NobelPrize?
    suspend fun findLaureates(year: Int, category: String): List<Laureate>
}