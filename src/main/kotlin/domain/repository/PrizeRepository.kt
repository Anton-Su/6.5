package domain.repository

import domain.model.Laureate
import domain.model.NobelPrize

interface PrizeRepository {
    fun getAll(): List<NobelPrize>
    fun findPrize(year: String, category: String): NobelPrize?
    fun findLaureates(year: String, category: String): List<Laureate>?
}