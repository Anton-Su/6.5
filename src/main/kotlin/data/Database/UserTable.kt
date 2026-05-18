@file:Suppress("unused")

package data.Database

import org.jetbrains.exposed.dao.id.IntIdTable

@Suppress("unused")
object UserTable : IntIdTable("user") {
    val username = varchar("username", 50).uniqueIndex()
    val gender = varchar("gender", 2)
    val age = integer("age")
    val password_hash = varchar("password_hash", 255)
}