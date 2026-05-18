package data.repository

import data.Database.NobelPrizeTable
import data.Database.UserFavoritePrizeTable
import data.Database.UserTable
import domain.model.NobelPrize
import domain.model.User
import domain.repository.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class UserRepositoryImpl : UserRepository {
    override suspend fun findByUsername(username: String): User? = newSuspendedTransaction {
        UserTable
            .selectAll()
            .where { UserTable.username eq username }
            .limit(1)
            .map { it.toUser() }
            .firstOrNull()
    }

    override suspend fun findById(id: Int): User? = newSuspendedTransaction {
        UserTable
            .selectAll()
            .where { UserTable.id eq id }
            .limit(1)
            .map { it.toUser() }
            .firstOrNull()
    }

    override suspend fun getFavorites(userId: Int): List<NobelPrize> = newSuspendedTransaction {
        loadFavorites(userId)
    }

    override suspend fun addFavorite(userId: Int, prizeId: Int): Boolean = newSuspendedTransaction {
        val userExists = UserTable.selectAll().where { UserTable.id eq userId }.any()
        if (!userExists) return@newSuspendedTransaction false

        val prizeExists = NobelPrizeTable.selectAll().where { NobelPrizeTable.id eq prizeId }.any()
        if (!prizeExists) return@newSuspendedTransaction false

        val alreadyExists = UserFavoritePrizeTable
            .selectAll()
            .where { (UserFavoritePrizeTable.userId eq userId) and (UserFavoritePrizeTable.prizeId eq prizeId) }
            .any()

        if (!alreadyExists) {
            UserFavoritePrizeTable.insert { row ->
                row[UserFavoritePrizeTable.userId] = userId
                row[UserFavoritePrizeTable.prizeId] = prizeId
            }
        }

        true
    }

    override suspend fun removeFavorite(userId: Int, prizeId: Int): Boolean = newSuspendedTransaction {
        val deleted = UserFavoritePrizeTable.deleteWhere {
            (UserFavoritePrizeTable.userId eq userId) and (UserFavoritePrizeTable.prizeId eq prizeId)
        }
        deleted > 0
    }
}

private fun loadFavorites(userId: Int): List<NobelPrize> {
    val prizeIds = UserFavoritePrizeTable
        .selectAll()
        .where { UserFavoritePrizeTable.userId eq userId }
        .map { it[UserFavoritePrizeTable.prizeId] }

    return prizeIds.mapNotNull { prizeId ->
        NobelPrizeTable
            .selectAll()
            .where { NobelPrizeTable.id eq prizeId }
            .limit(1)
            .map { it.toPrize() }
            .firstOrNull()
    }
}

private fun ResultRow.toUser(): User = User(
    id = this[UserTable.id].value,
    passwordHash = this[UserTable.password_hash],
    username = this[UserTable.username],
    gender = this[UserTable.gender],
    age = this[UserTable.age],
    favoritePrizes = loadFavorites(this[UserTable.id].value)
)

private fun ResultRow.toPrize(): NobelPrize = NobelPrize(
    id = this[NobelPrizeTable.id].value,
    year = this[NobelPrizeTable.year],
    category = this[NobelPrizeTable.category]
)
