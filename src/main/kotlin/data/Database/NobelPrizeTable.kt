@file:Suppress("unused")

package data.Database

import org.jetbrains.exposed.dao.id.IntIdTable

object NobelPrizeTable : IntIdTable("nobel_prize") {
    val year = integer("year")
    val category = varchar("category", 100)
}

