package domain.usecases

import domain.repository.UserRepository

class AddFavoritePrizeUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int, prizeId: Int): Boolean {
        return userRepository.addFavorite(userId, prizeId)
    }
}

