package domain.repository

//import domain.model.Country
import domain.model.User

interface UserRepository {
    suspend fun findByUsername(username: String): User?
    suspend fun findById(id: Int): User?
    // suspend fun getUserCountries(userId: Int): List<Country>
}