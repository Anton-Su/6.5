package com.example.data

//import domain.model.Laureate
//import com.example.domain.NobelPrize
//
//interface PrizeRepository {
//    fun getAll(): List<NobelPrize>
//    fun find(year: String, category: String): NobelPrize?
//    fun findLaureates(year: String, category: String): List<Laureate>?
//}
//
//class InMemoryPrizeRepository : PrizeRepository {
//    private val prizes: List<NobelPrize>
//
//    init {
//        // Sample in-memory data; extend as needed
//        val laureate1 = Laureate(
//            id = "1",
//            fullName = "Albert Einstein",
//            year = "1921",
//            category = "physics",
//            motivation = "for his services to Theoretical Physics, and especially for his discovery of the law of the photoelectric effect",
//            birthCountry = "Germany",
//            birthPlace = "Ulm",
//            portraitUrl = "https://example.org/images/einstein.jpg"
//        )
//
//        val laureate2 = Laureate(
//            id = "2",
//            fullName = "Marie Curie",
//            year = "1903",
//            category = "physics",
//            motivation = "in recognition of the extraordinary services they have rendered by their joint researches on the radiation phenomena",
//            birthCountry = "Poland",
//            birthPlace = "Warsaw",
//            portraitUrl = "https://example.org/images/curie.jpg"
//        )
//
//        prizes = listOf(
//            NobelPrize(year = "1921", category = "physics", laureates = listOf(laureate1)),
//            NobelPrize(year = "1903", category = "physics", laureates = listOf(laureate2))
//        )
//    }
//
//    override fun getAll(): List<NobelPrize> = prizes
//
//    override fun find(year: String, category: String): NobelPrize? =
//        prizes.firstOrNull { it.year == year && it.category.equals(category, ignoreCase = true) }
//
//    override fun findLaureates(year: String, category: String): List<Laureate>? =
//        find(year, category)?.laureates
//}
//
