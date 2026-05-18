package domain.usecases

import domain.model.User
import domain.repository.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String): User? {
        return userRepository.findByUsername(username)
    }
}

