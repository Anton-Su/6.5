package data.repository

import domain.model.User
import domain.repository.UserRepository


class UserRepositoryImpl : UserRepository {
    val users: List<User> = List(10) { index ->
        User(
            id = index + 1,
            username = "user$index",
            role = if (index == 0) "ADMIN" else "USER"
        )
    }


    override suspend fun findByUsername(username: String): User? {
        return users.find { user -> user.username == username }
    }


    override suspend fun findById(id: Int): User? {
        TODO("Not yet implemented")
    }
}