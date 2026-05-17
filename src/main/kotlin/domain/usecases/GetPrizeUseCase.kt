package domain.usecases

import domain.model.Laureate
import domain.model.NobelPrize
import domain.repository.PrizeRepository

class GetPrizeUseCase(
    private val prizeRepository: PrizeRepository
) {
    suspend operator fun invoke(): List<NobelPrize?> {
        return prizeRepository.getAll()
    }

    suspend fun getById(year: String, category: String): NobelPrize? {
        return prizeRepository.findPrize(year, category)
    }


}