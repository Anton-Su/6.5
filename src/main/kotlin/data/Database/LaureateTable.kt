@file:Suppress("unused")

package data.Database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object LaureateTable : IntIdTable("laureate") {
    val nobelPrizeId = integer("nobel_prize_id").references(
        ref = NobelPrizeTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val fullName = varchar("full_name", 255)
    val motivation = text("motivation")
    val portion = varchar("portion", 50)
    val portraitUrl = varchar("portrait_url", 500).nullable()
}