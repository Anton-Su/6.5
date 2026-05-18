package data.repository

import data.Database.LaureateTable
import data.Database.NobelPrizeTable
import domain.model.Laureate
import domain.model.NobelPrize
import domain.repository.PrizeRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class PrizeRepositoryImpl : PrizeRepository {
    override suspend fun getAll(): List<NobelPrize> = newSuspendedTransaction {
        NobelPrizeTable
            .selectAll()
            .orderBy(NobelPrizeTable.year to SortOrder.ASC, NobelPrizeTable.category to SortOrder.ASC)
            .map { it.toPrize() }
    }

    override suspend fun findPrize(year: Int, category: String): NobelPrize? = newSuspendedTransaction {
        NobelPrizeTable
            .selectAll()
            .where { (NobelPrizeTable.year eq year) and (NobelPrizeTable.category eq category) }
            .limit(1)
            .map { it.toPrize() }
            .firstOrNull()
    }

    override suspend fun findLaureates(year: Int, category: String): List<Laureate> = newSuspendedTransaction {
        val prizeId = NobelPrizeTable
            .selectAll()
            .where { (NobelPrizeTable.year eq year) and (NobelPrizeTable.category eq category) }
            .limit(1)
            .map { it[NobelPrizeTable.id].value }
            .firstOrNull() ?: return@newSuspendedTransaction emptyList()

        LaureateTable
            .selectAll()
            .where { LaureateTable.nobelPrizeId eq prizeId }
            .orderBy(LaureateTable.id to SortOrder.ASC)
            .map { it.toLaureate() }
    }
}

private fun ResultRow.toPrize(): NobelPrize = NobelPrize(
    id = this[NobelPrizeTable.id].value,
    year = this[NobelPrizeTable.year],
    category = this[NobelPrizeTable.category]
)

private fun ResultRow.toLaureate(): Laureate = Laureate(
    id = this[LaureateTable.id].value,
    prizeId = this[LaureateTable.nobelPrizeId],
    fullName = this[LaureateTable.fullName],
    motivation = this[LaureateTable.motivation],
    portion = this[LaureateTable.portion],
    portraitUrl = this[LaureateTable.portraitUrl]
)
