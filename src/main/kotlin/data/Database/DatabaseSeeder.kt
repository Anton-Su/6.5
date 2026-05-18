package data.Database

import Security.PasswordHasher
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll

object DatabaseSeeder {
    fun seed() {
        if (NobelPrizeTable.selectAll().any()) return

        val chemistry1901 = insertPrize(1901, "Chemistry")
        val literature1901 = insertPrize(1901, "Literature")
        val peace1901 = insertPrize(1902, "Peace")
        val physics1901 = insertPrize(1901, "Physics")
        val medicine1901 = insertPrize(1901, "Physiology or Medicine")

        insertLaureate(
            prizeId = chemistry1901,
            fullName = "Jacobus Henricus van 't Hoff",
            motivation = "for the discovery of the laws of chemical dynamics and osmotic pressure in solutions",
            portion = "1",
            portraitUrl = "https://picsum.photos/160.jpg"
        )
        insertLaureate(
            prizeId = literature1901,
            fullName = "Sully Prudhomme",
            motivation = "for his poetic composition, which gives evidence of lofty idealism",
            portion = "1",
            portraitUrl = "https://picsum.photos/569.jpg"
        )
        insertLaureate(
            prizeId = peace1901,
            fullName = "Jean Henry Dunant",
            motivation = "for his humanitarian efforts to help wounded soldiers and create international understanding",
            portion = "1/2",
            portraitUrl = "https://picsum.photos/462.jpg"
        )
        insertLaureate(
            prizeId = peace1901,
            fullName = "Frédéric Passy",
            motivation = "for his lifelong work for international peace conferences, diplomacy and arbitration",
            portion = "1/2",
            portraitUrl = "https://picsum.photos/463.jpg"
        )
        insertLaureate(
            prizeId = physics1901,
            fullName = "Wilhelm Conrad Röntgen",
            motivation = "for the discovery of the remarkable rays subsequently named after him",
            portion = "1",
            portraitUrl = "https://picsum.photos/1.jpg"
        )
        insertLaureate(
            prizeId = medicine1901,
            fullName = "Emil Adolf von Behring",
            motivation = "for his work on serum therapy against diphtheria",
            portion = "1",
            portraitUrl = "https://picsum.photos/293.jpg"
        )

        val user0 = insertUser("user0", "M", 15, "emilyspass")
        val user1 = insertUser("user1", "F", 18, "1111")

        addFavorite(user0, chemistry1901)
        addFavorite(user0, physics1901)
        addFavorite(user1, literature1901)
    }

    private fun insertPrize(year: Int, category: String): Int =
        NobelPrizeTable.insertAndGetId {
            it[NobelPrizeTable.year] = year
            it[NobelPrizeTable.category] = category
        }.value

    private fun insertLaureate(
        prizeId: Int,
        fullName: String,
        motivation: String,
        portion: String,
        portraitUrl: String?
    ): Int = LaureateTable.insertAndGetId {
        it[nobelPrizeId] = prizeId
        it[LaureateTable.fullName] = fullName
        it[LaureateTable.motivation] = motivation
        it[LaureateTable.portion] = portion
        it[LaureateTable.portraitUrl] = portraitUrl
    }.value

    private fun insertUser(username: String, gender: String, age: Int, rawPassword: String): Int =
        UserTable.insertAndGetId {
            it[UserTable.username] = username
            it[UserTable.gender] = gender
            it[UserTable.age] = age
            it[UserTable.password_hash] = PasswordHasher.hash(rawPassword)
        }.value

    private fun addFavorite(userId: Int, prizeId: Int) {
        UserFavoritePrizeTable.insert {
            it[UserFavoritePrizeTable.userId] = userId
            it[UserFavoritePrizeTable.prizeId] = prizeId
        }
    }
}



