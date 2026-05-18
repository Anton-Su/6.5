package domain.usecases

import domain.repository.UserRepository

class RemoveFavoritePrizeUseCase(
	private val userRepository: UserRepository
) {
	suspend operator fun invoke(userId: Int, prizeId: Int): Boolean {
		return userRepository.removeFavorite(userId, prizeId)
	}
}


