package domain.usecases

import domain.model.NobelPrize
import domain.repository.UserRepository

class GetUserFavoritesUseCase(
	private val userRepository: UserRepository
) {
	suspend operator fun invoke(userId: Int): List<NobelPrize> {
		return userRepository.getFavorites(userId)
	}
}


