package domain.usecases

import Security.JwtConfig
import Security.PasswordHasher
//import data.database.UserTable
import domain.repository.UserRepository
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class LoginUseCase(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    suspend fun login(username: String, password: String): String? {
        val user = userRepository.findByUsername(username) ?: return null


        val passwordHash = "1111"
        // val passwordHash = getPasswordHashByUsername(username) ?: return null
        // if (!passwordHasher.verify(password, passwordHash)) return null
        return JwtConfig.generateToken(user.username, user.role)


    }



//    private suspend fun getPasswordHashByUsername(username: String): String?{
//
//
//    }

//    private suspend fun getPasswordHashByUsername(username: String): String? = newSuspendedTransaction {
//        UserTable.selectAll().where { UserTable.username eq username }
//            .map { it[UserTable.passwordHash] }
//            .firstOrNull()
//    }
}