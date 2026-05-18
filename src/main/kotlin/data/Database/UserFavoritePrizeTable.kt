package data.Database

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption


object UserFavoritePrizeTable : IntIdTable("user_favorite_prize") {
    val userId = integer("user_id").references(
        ref = UserTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
    val prizeId = integer("prize_id").references(
        ref = NobelPrizeTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.CASCADE
    )
}


