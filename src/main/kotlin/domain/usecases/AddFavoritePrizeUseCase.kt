package domain.usecases

import domain.repository.UserRepository

class AddFavoritePrizeUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int, prizeId: String): Boolean {
        return userRepository.addFavorite(userId, prizeId)
    }
}

