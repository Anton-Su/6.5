package domain.usecases

import Security.JwtConfig
import Security.PasswordHasher
import domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    suspend fun login(username: String, password: String): String? {
        val user = userRepository.findByUsername(username) ?: return null
        val passwordHash = getPasswordHashByUsername(username) ?: return null
        if (!passwordHasher.verify(password, passwordHash)) return null
        return JwtConfig.generateToken(user.username)
    }

    private suspend fun getPasswordHashByUsername(username: String): String? {
        val user = userRepository.findByUsername(username) ?: return null
        return user.passwordHash
    }
}