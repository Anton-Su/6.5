package data.repository

import domain.model.NobelPrize
import domain.model.User
import domain.repository.PrizeRepository
import domain.repository.UserRepository

class UserRepositoryImpl(private val prizeRepository: PrizeRepository) : UserRepository {
    // mutable list so we can update favorites in-memory for this sample implementation
    private val users: MutableList<User> = MutableList(10) { index ->
        User(
            id = index + 1,
            username = "user$index",
            gender = "M",
            age = "15",
            favoritePrizes = emptyList(),
        )
    }

    override suspend fun findByUsername(username: String): User? {
        return users.find { user -> user.username == username }
    }

    override suspend fun findById(id: Int): User? {
        return users.find { it.id == id }
    }

    override suspend fun getFavorites(userId: Int): List<NobelPrize> {
        val user = findById(userId) ?: return emptyList()
        return user.favoritePrizes
    }

    override suspend fun addFavorite(userId: Int, prizeId: String): Boolean {
        val userIndex = users.indexOfFirst { it.id == userId }
        if (userIndex == -1) return false

        val prize: NobelPrize? = prizeRepository.getAll().firstOrNull { it.id == prizeId }
        if (prize == null) return false

        val user = users[userIndex]
        if (user.favoritePrizes.any { it.id == prizeId }) return true // already present

        val updated = user.copy(favoritePrizes = user.favoritePrizes + prize)
        users[userIndex] = updated
        return true
    }

    override suspend fun removeFavorite(userId: Int, prizeId: String): Boolean {
        val userIndex = users.indexOfFirst { it.id == userId }
        if (userIndex == -1) return false

        val user = users[userIndex]
        if (user.favoritePrizes.none { it.id == prizeId }) return false

        val updated = user.copy(favoritePrizes = user.favoritePrizes.filterNot { it.id == prizeId })
        users[userIndex] = updated
        return true
    }
}