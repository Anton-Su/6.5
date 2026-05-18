package domain.usecases

import domain.model.Laureate
import domain.model.NobelPrize
import domain.repository.PrizeRepository

class GetPrizeUseCase(
    private val prizeRepository: PrizeRepository
) {
    suspend operator fun invoke(): List<NobelPrize> = prizeRepository.getAll()

    suspend fun getById(year: Int, category: String): NobelPrize? =
        prizeRepository.findPrize(year, category)

    suspend fun getLaureates(year: Int, category: String): List<Laureate> =
        prizeRepository.findLaureates(year, category)
}