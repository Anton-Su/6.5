package data.repository

import domain.model.Laureate
import domain.model.NobelPrize
import domain.repository.PrizeRepository

class PrizeRepositoryImpl : PrizeRepository {
    private val prizes: List<NobelPrize>

    init {
        // Sample in-memory data; extend as needed
        val laureate160 = Laureate(
            id = "160",
            fullName = "Jacobus Henricus van 't Hoff",
            motivation = "in recognition of the extraordinary services he has rendered by the discovery of the laws of chemical dynamics and osmotic pressure in solutions",

        portraitUrl = "https://example.org/images/laureate-160.jpg"
        )

        val laureate569 = Laureate(
            id = "569",
            fullName = "Sully Prudhomme",
            motivation = "in special recognition of his poetic composition, which gives evidence of lofty idealism, artistic perfection and a rare combination of the qualities of both heart and intellect"
        , portraitUrl = "https://example.org/images/laureate-569.jpg"
        )

        val laureate462 = Laureate(
            id = "462",
            fullName = "Jean Henry Dunant",
            motivation = "for his humanitarian efforts to help wounded soldiers and create international understanding"
        , portraitUrl = "https://example.org/images/laureate-462.jpg"
        )

        val laureate463 = Laureate(
            id = "463",
            fullName = "Frédéric Passy",
            motivation = "for his lifelong work for international peace conferences, diplomacy and arbitration"
        , portraitUrl = "https://example.org/images/laureate-463.jpg"
        )

        val laureate1 = Laureate(
            id = "1",
            fullName = "Wilhelm Conrad Röntgen",
            motivation = "in recognition of the extraordinary services he has rendered by the discovery of the remarkable rays subsequently named after him"
        , portraitUrl = "https://example.org/images/laureate-1.jpg"
        )

        val laureate293 = Laureate(
            id = "293",
            fullName = "Emil Adolf von Behring",
            motivation = "for his work on serum therapy, especially its application against diphtheria, by which he has opened a new road in the domain of medical science and thereby placed in the hands of the physician a victorious weapon against illness and deaths"
        , portraitUrl = "https://example.org/images/laureate-293.jpg"
        )

        val laureate161 = Laureate(
            id = "161",
            fullName = "Hermann Emil Fischer",
            motivation = "in recognition of the extraordinary services he has rendered by his work on sugar and purine syntheses"
        , portraitUrl = "https://example.org/images/laureate-161.jpg"
        )

        val laureate571 = Laureate(
            id = "571",
            fullName = "Christian Matthias Theodor Mommsen",
            motivation = "the greatest living master of the art of historical writing, with special reference to his monumental work, <I>A history of Rome</I>"
        , portraitUrl = "https://example.org/images/laureate-571.jpg"
        )

        val laureate464 = Laureate(
            id = "464",
            fullName = "Élie Ducommun",
            motivation = "for his untiring and skilful directorship of the Bern Peace Bureau"
        , portraitUrl = "https://example.org/images/laureate-464.jpg"
        )

        val laureate465 = Laureate(
            id = "465",
            fullName = "Charles Albert Gobat",
            motivation = "for his eminently practical administration of the Inter-Parliamentary Union"
        , portraitUrl = "https://example.org/images/laureate-465.jpg"
        )

        val laureate2 = Laureate(
            id = "2",
            fullName = "Hendrik Antoon Lorentz",
            motivation = "in recognition of the extraordinary service they rendered by their researches into the influence of magnetism upon radiation phenomena"
        , portraitUrl = "https://example.org/images/laureate-2.jpg"
        )

        val laureate3 = Laureate(
            id = "3",
            fullName = "Pieter Zeeman",
            motivation = "in recognition of the extraordinary service they rendered by their researches into the influence of magnetism upon radiation phenomena"
        , portraitUrl = "https://example.org/images/laureate-3.jpg"
        )

        val laureate294 = Laureate(
            id = "294",
            fullName = "Ronald Ross",
            motivation = "for his work on malaria, by which he has shown how it enters the organism and thereby has laid the foundation for successful research on this disease and methods of combating it"
        , portraitUrl = "https://example.org/images/laureate-294.jpg"
        )

        val laureate162 = Laureate(
            id = "162",
            fullName = "Svante August Arrhenius",
            motivation = "in recognition of the extraordinary services he has rendered to the advancement of chemistry by his electrolytic theory of dissociation"
        , portraitUrl = "https://example.org/images/laureate-162.jpg"
        )

        val laureate572 = Laureate(
            id = "572",
            fullName = "Bjørnstjerne Martinus Bjørnson",
            motivation = "as a tribute to his noble, magnificent and versatile poetry, which has always been distinguished by both the freshness of its inspiration and the rare purity of its spirit"
        , portraitUrl = "https://example.org/images/laureate-572.jpg"
        )

        val laureate466 = Laureate(
            id = "466",
            fullName = "William Randal Cremer",
            motivation = "for his longstanding and devoted effort in favour of the ideas of peace and arbitration"
        , portraitUrl = "https://example.org/images/laureate-466.jpg"
        )

        val laureate4 = Laureate(
            id = "4",
            fullName = "Antoine Henri Becquerel",
            motivation = "in recognition of the extraordinary services he has rendered by his discovery of spontaneous radioactivity"
        , portraitUrl = "https://example.org/images/laureate-4.jpg"
        )

        val laureate5 = Laureate(
            id = "5",
            fullName = "Pierre Curie",
            motivation = "in recognition of the extraordinary services they have rendered by their joint researches on the radiation phenomena discovered by Professor Henri Becquerel"
        , portraitUrl = "https://example.org/images/laureate-5.jpg"
        )

        val laureate6 = Laureate(
            id = "6",
            fullName = "Marie Curie, née Skłodowska",
            motivation = "in recognition of the extraordinary services they have rendered by their joint researches on the radiation phenomena discovered by Professor Henri Becquerel"
         ,portraitUrl = "https://example.org/images/laureate-6.jpg"
        )

        val laureate295 = Laureate(
            id = "295",
            fullName = "Niels Ryberg Finsen",
            motivation = "in recognition of his contribution to the treatment of diseases, especially lupus vulgaris, with concentrated light radiation, whereby he has opened a new avenue for medical science"
        , portraitUrl = "https://example.org/images/laureate-295.jpg"
        )

        val laureate163 = Laureate(
            id = "163",
            fullName = "Sir William Ramsay",
            motivation = "in recognition of his services in the discovery of the inert gaseous elements in air, and his determination of their place in the periodic system"
        , portraitUrl = "https://example.org/images/laureate-163.jpg"
        )

        val laureate573 = Laureate(
            id = "573",
            fullName = "Frédéric Mistral",
            motivation = "in recognition of the fresh originality and true inspiration of his poetic production, which faithfully reflects the natural scenery and native spirit of his people, and, in addition, his significant work as a Provençal philologist"
        , portraitUrl = "https://example.org/images/laureate-573.jpg"
        )

        val laureate574 = Laureate(
            id = "574",
            fullName = "José Echegaray y Eizaguirre",
            motivation = "in recognition of the numerous and brilliant compositions which, in an individual and original manner, have revived the great traditions of the Spanish drama"
        , portraitUrl = "https://example.org/images/laureate-574.jpg"
        )

        val laureate467 = Laureate(
            id = "467",
            fullName = "Institut de droit international",
            motivation = "for its striving in public law to develop peaceful ties between nations and to make the laws of war more humane"
        , portraitUrl = "https://example.org/images/laureate-467.jpg"
        )

        val laureate8 = Laureate(
            id = "8",
            fullName = "Lord Rayleigh (John William Strutt)",
            motivation = "for his investigations of the densities of the most important gases and for his discovery of argon in connection with these studies"
        , portraitUrl = "https://example.org/images/laureate-8.jpg"
        )

        val laureate296 = Laureate(
            id = "296",
            fullName = "Ivan Petrovich Pavlov",
            motivation = "in recognition of his work on the physiology of digestion, through which knowledge on vital aspects of the subject has been transformed and enlarged"
        , portraitUrl = "https://example.org/images/laureate-296.jpg"
        )

        val laureate164 = Laureate(
            id = "164",
            fullName = "Johann Friedrich Wilhelm Adolf von Baeyer",
            motivation = "in recognition of his services in the advancement of organic chemistry and the chemical industry, through his work on organic dyes and hydroaromatic compounds"
        , portraitUrl = "https://example.org/images/laureate-164.jpg"
        )

        val laureate575 = Laureate(
            id = "575",
            fullName = "Henryk Sienkiewicz",
            motivation = "because of his outstanding merits as an epic writer"
        , portraitUrl = "https://example.org/images/laureate-575.jpg"
        )

        val laureate468 = Laureate(
            id = "468",
            fullName = "Baroness Bertha Sophie Felicita von Suttner, née Countess Kinsky von Chinic und Tettau",
            motivation = "for her audacity to oppose the horrors of war"
        , portraitUrl = "https://example.org/images/laureate-468.jpg"
        )

        val laureate9 = Laureate(
            id = "9",
            fullName = "Philipp Eduard Anton von Lenard",
            motivation = "for his work on cathode rays"
        , portraitUrl = "https://example.org/images/laureate-9.jpg"
        )

        val laureate297 = Laureate(
            id = "297",
            fullName = "Robert Koch",
            motivation = "for his investigations and discoveries in relation to tuberculosis"
        , portraitUrl = "https://example.org/images/laureate-297.jpg"
        )

        val laureate165 = Laureate(
            id = "165",
            fullName = "Henri Moissan",
            motivation = "in recognition of the great services rendered by him in his investigation and isolation of the element fluorine, and for the adoption in the service of science of the electric furnace called after him"
        ,portraitUrl = "https://example.org/images/laureate-165.jpg"
        )

        val laureate576 = Laureate(
            id = "576",
            fullName = "Giosuè Carducci",
            motivation = "not only in consideration of his deep learning and critical research, but above all as a tribute to the creative energy, freshness of style, and lyrical force which characterize his poetic masterpieces"
        ,portraitUrl = "https://example.org/images/laureate-576.jpg"
        )

        val laureate470 = Laureate(
            id = "470",
            fullName = "Theodore Roosevelt Jr.",
            motivation = "for his role in bringing to an end the bloody war recently waged between two of the world's great powers, Japan and Russia"
        , portraitUrl = "https://example.org/images/laureate-470.jpg"
        )

        val laureate10 = Laureate(
            id = "10",
            fullName = "Joseph John Thomson",
            motivation = "in recognition of the great merits of his theoretical and experimental investigations on the conduction of electricity by gases"
        , portraitUrl = "https://example.org/images/laureate-10.jpg"
        )

        val laureate298 = Laureate(
            id = "298",
            fullName = "Camillo Golgi",
            motivation = "in recognition of their work on the structure of the nervous system"
        , portraitUrl = "https://example.org/images/laureate-298.jpg"
        )

        val laureate299 = Laureate(
            id = "299",
            fullName = "Santiago Ramón y Cajal",
            motivation = "in recognition of their work on the structure of the nervous system"
        , portraitUrl = "https://example.org/images/laureate-299.jpg"
        )

        val laureate166 = Laureate(
            id = "166",
            fullName = "Eduard Buchner",
            motivation = "for his biochemical researches and his discovery of cell-free fermentation"
        , portraitUrl = "https://example.org/images/laureate-166.jpg"
        )

        val laureate577 = Laureate(
            id = "577",
            fullName = "Rudyard Kipling",
            motivation = "in consideration of the power of observation, originality of imagination, virility of ideas and remarkable talent for narration which characterize the creations of this world-famous author"
        , portraitUrl = "https://example.org/images/laureate-577.jpg"
        )

        val laureate471 = Laureate(
            id = "471",
            fullName = "Ernesto Teodoro Moneta",
            motivation = "for his work in the press and in peace meetings, both public and private, for an understanding between France and Italy"
        ,portraitUrl = "https://example.org/images/laureate-471.jpg"
        )

        val laureate472 = Laureate(
            id = "472",
            fullName = "Louis Renault",
            motivation = "for his decisive influence upon the conduct and outcome of the Hague and Geneva Conferences"
        ,portraitUrl = "https://example.org/images/laureate-472.jpg"
        )

        val laureate11 = Laureate(
            id = "11",
            fullName = "Albert Abraham Michelson",
            motivation = "for his optical precision instruments and the spectroscopic and metrological investigations carried out with their aid"
        ,portraitUrl = "https://example.org/images/laureate-11.jpg"
        )

        val laureate300 = Laureate(
            id = "300",
            fullName = "Charles Louis Alphonse Laveran",
            motivation = "in recognition of his work on the role played by protozoa in causing diseases"
        ,portraitUrl = "https://example.org/images/laureate-300.jpg"
        )

        val laureate167 = Laureate(
            id = "167",
            fullName = "Ernest Rutherford",
            motivation = "for his investigations into the disintegration of the elements, and the chemistry of radioactive substances"
        ,portraitUrl = "https://example.org/images/laureate-167.jpg"
        )

        val laureate578 = Laureate(
            id = "578",
            fullName = "Rudolf Christoph Eucken",
            motivation = "in recognition of his earnest search for truth, his penetrating power of thought, his wide range of vision, and the warmth and strength in presentation with which in his numerous works he has vindicated and developed an idealistic philosophy of life"
        ,portraitUrl = "https://example.org/images/laureate-578.jpg"
        )

        val laureate473 = Laureate(
            id = "473",
            fullName = "Klas Pontus Arnoldson",
            motivation = "for their long time work for the cause of peace as politicians, peace society leaders, orators and authors"
        ,portraitUrl = "https://example.org/images/laureate-473.jpg"
        )

        val laureate474 = Laureate(
            id = "474",
            fullName = "Fredrik Bajer",
            motivation = "for their long time work for the cause of peace as politicians, peace society leaders, orators and authors"
        ,portraitUrl = "https://example.org/images/laureate-474.jpg"
        )

        val laureate12 = Laureate(
            id = "12",
            fullName = "Gabriel Lippmann",
            motivation = "for his method of reproducing colours photographically based on the phenomenon of interference"
        ,portraitUrl = "https://example.org/images/laureate-12.jpg"
        )

        val laureate301 = Laureate(
            id = "301",
            fullName = "Ilya Ilyich Mechnikov",
            motivation = "in recognition of their work on immunity"
        ,portraitUrl = "https://example.org/images/laureate-301.jpg"
        )

        val laureate302 = Laureate(
            id = "302",
            fullName = "Paul Ehrlich",
            motivation = "in recognition of their work on immunity"
        ,portraitUrl = "https://example.org/images/laureate-302.jpg"
        )

        val laureate168 = Laureate(
            id = "168",
            fullName = "Wilhelm Ostwald",
            motivation = "in recognition of his work on catalysis and for his investigations into the fundamental principles governing chemical equilibria and rates of reaction"
        ,portraitUrl = "https://example.org/images/laureate-168.jpg"
        )

        val laureate579 = Laureate(
            id = "579",
            fullName = "Selma Ottilia Lovisa Lagerlöf",
            motivation = "in appreciation of the lofty idealism, vivid imagination and spiritual perception that characterize her writings"
        ,portraitUrl = "https://example.org/images/laureate-579.jpg"
        )

        val laureate475 = Laureate(
            id = "475",
            fullName = "Auguste Marie François Beernaert",
            motivation = "for their prominent position in the international movement for peace and arbitration",
            portraitUrl = "https://example.org/images/laureate-475.jpg"
        )

        val laureate476 = Laureate(
            id = "476",
            fullName = "Paul Henri Benjamin Balluet d'Estournelles de Constant, Baron de Constant de Rebecque",
            motivation = "for their prominent position in the international movement for peace and arbitration",
        portraitUrl = "https://example.org/images/laureate-476.jpg"
        )

        val laureate13 = Laureate(
            id = "13",
            fullName = "Guglielmo Marconi",
            motivation = "in recognition of their contributions to the development of wireless telegraphy"
        , portraitUrl = "https://example.org/images/laureate-13.jpg"
        )

        val laureate14 = Laureate(
            id = "14",
            fullName = "Karl Ferdinand Braun",
            motivation = "in recognition of their contributions to the development of wireless telegraphy"
        , portraitUrl = "https://example.org/images/laureate-14.jpg"
        )

        val laureate303 = Laureate(
            id = "303",
            fullName = "Emil Theodor Kocher",
            motivation = "for his work on the physiology, pathology and surgery of the thyroid gland"
        , portraitUrl = "https://example.org/images/laureate-303.jpg"
        )

        val laureate169 = Laureate(
            id = "169",
            fullName = "Otto Wallach",
            motivation = "in recognition of his services to organic chemistry and the chemical industry by his pioneer work in the field of alicyclic compounds"
        , portraitUrl = "https://example.org/images/laureate-169.jpg"
        )

        val laureate580 = Laureate(
            id = "580",
            fullName = "Paul Johann Ludwig Heyse",
            motivation = "as a tribute to the consummate artistry, permeated with idealism, which he has demonstrated during his long productive career as a lyric poet, dramatist, novelist and writer of world-renowned short stories"
        , portraitUrl = "https://example.org/images/laureate-580.jpg"
        )

        val laureate477 = Laureate(
            id = "477",
            fullName = "Bureau international permanent de la Paix",
            motivation = "for acting as a link between the peace societies of the various countries, and helping them to organize the world rallies of the international peace movement"
        , portraitUrl = "https://example.org/images/laureate-477.jpg"
        )

        val laureate15 = Laureate(
            id = "15",
            fullName = "Johannes Diderik van der Waals",
            motivation = "for his work on the equation of state for gases and liquids"
        , portraitUrl = "https://example.org/images/laureate-15.jpg"
        )

        val laureate304 = Laureate(
            id = "304",
            fullName = "Albrecht Kossel",
            motivation = "in recognition of the contributions to our knowledge of cell chemistry made through his work on proteins, including the nucleic substances"
        , portraitUrl = "https://example.org/images/laureate-304.jpg"
        )

        prizes = listOf(
            NobelPrize(year = "1901", category = "Chemistry", laureates = listOf(laureate160), id = "1901_Chemistry"),
            NobelPrize(year = "1901", category = "Literature", laureates = listOf(laureate569), id = "1901_Literature"),
            NobelPrize(year = "1901", category = "Peace", laureates = listOf(laureate462, laureate463), id = "1901_Peace"),
            NobelPrize(year = "1901", category = "Physics", laureates = listOf(laureate1), id = "1901_Physics"),
            NobelPrize(year = "1901", category = "Physiology or Medicine", laureates = listOf(laureate293), id = "1901_Physiology or Medicine"),
            NobelPrize(year = "1902", category = "Chemistry", laureates = listOf(laureate161), id = "1902_Chemistry"),
            NobelPrize(year = "1902", category = "Literature", laureates = listOf(laureate571), id = "1902_Literature"),
            NobelPrize(year = "1902", category = "Peace", laureates = listOf(laureate464, laureate465), id = "1902_Peace"),
            NobelPrize(year = "1902", category = "Physics", laureates = listOf(laureate2, laureate3), id = "1902_Physics"),
            NobelPrize(year = "1902", category = "Physiology or Medicine", laureates = listOf(laureate294), id = "1902_Physiology or Medicine"),
            NobelPrize(year = "1903", category = "Chemistry", laureates = listOf(laureate162), id = "1903_Chemistry"),
            NobelPrize(year = "1903", category = "Literature", laureates = listOf(laureate572), id = "1903_Literature"),
            NobelPrize(year = "1903", category = "Peace", laureates = listOf(laureate466), id = "1903_Peace"),
            NobelPrize(year = "1903", category = "Physics", laureates = listOf(laureate4, laureate5, laureate6), id = "1903_Physics"),
            NobelPrize(year = "1903", category = "Physiology or Medicine", laureates = listOf(laureate295), id = "1903_Physiology or Medicine"),
            NobelPrize(year = "1904", category = "Chemistry", laureates = listOf(laureate163), id = "1904_Chemistry"),
            NobelPrize(year = "1904", category = "Literature", laureates = listOf(laureate573, laureate574), id = "1904_Literature"),
            NobelPrize(year = "1904", category = "Peace", laureates = listOf(laureate467), id = "1904_Peace"),
            NobelPrize(year = "1904", category = "Physics", laureates = listOf(laureate8), id = "1904_Physics"),
            NobelPrize(year = "1904", category = "Physiology or Medicine", laureates = listOf(laureate296), id = "1904_Physiology or Medicine"),
            NobelPrize(year = "1905", category = "Chemistry", laureates = listOf(laureate164), id = "1905_Chemistry"),
            NobelPrize(year = "1905", category = "Literature", laureates = listOf(laureate575), id = "1905_Literature"),
            NobelPrize(year = "1905", category = "Peace", laureates = listOf(laureate468), id = "1905_Peace"),
            NobelPrize(year = "1905", category = "Physics", laureates = listOf(laureate9), id = "1905_Physics"),
            NobelPrize(year = "1905", category = "Physiology or Medicine", laureates = listOf(laureate297), id = "1905_Physiology or Medicine"),
            NobelPrize(year = "1906", category = "Chemistry", laureates = listOf(laureate165), id = "1906_Chemistry"),
            NobelPrize(year = "1906", category = "Literature", laureates = listOf(laureate576), id = "1906_Literature"),
            NobelPrize(year = "1906", category = "Peace", laureates = listOf(laureate470), id = "1906_Peace"),
            NobelPrize(year = "1906", category = "Physics", laureates = listOf(laureate10), id = "1906_Physics"),
            NobelPrize(year = "1906", category = "Physiology or Medicine", laureates = listOf(laureate298, laureate299), id = "1906_Physiology or Medicine"),
            NobelPrize(year = "1907", category = "Chemistry", laureates = listOf(laureate166), id = "1907_Chemistry"),
            NobelPrize(year = "1907", category = "Literature", laureates = listOf(laureate577), id = "1907_Literature"),
            NobelPrize(year = "1907", category = "Peace", laureates = listOf(laureate471, laureate472), id = "1907_Peace"),
            NobelPrize(year = "1907", category = "Physics", laureates = listOf(laureate11), id = "1907_Physics"),
            NobelPrize(year = "1907", category = "Physiology or Medicine", laureates = listOf(laureate300), id = "1907_Physiology or Medicine"),
            NobelPrize(year = "1908", category = "Chemistry", laureates = listOf(laureate167), id = "1908_Chemistry"),
            NobelPrize(year = "1908", category = "Literature", laureates = listOf(laureate578), id = "1908_Literature"),
            NobelPrize(year = "1908", category = "Peace", laureates = listOf(laureate473, laureate474), id = "1908_Peace"),
            NobelPrize(year = "1908", category = "Physics", laureates = listOf(laureate12), id = "1908_Physics"),
            NobelPrize(year = "1908", category = "Physiology or Medicine", laureates = listOf(laureate301, laureate302), id = "1908_Physiology or Medicine"),
            NobelPrize(year = "1909", category = "Chemistry", laureates = listOf(laureate168), id = "1909_Chemistry"),
            NobelPrize(year = "1909", category = "Literature", laureates = listOf(laureate579), id = "1909_Literature"),
            NobelPrize(year = "1909", category = "Peace", laureates = listOf(laureate475, laureate476), id = "1909_Peace"),
            NobelPrize(year = "1909", category = "Physics", laureates = listOf(laureate13, laureate14), id = "1909_Physics"),
            NobelPrize(year = "1909", category = "Physiology or Medicine", laureates = listOf(laureate303), id = "1909_Physiology or Medicine"),
            NobelPrize(year = "1910", category = "Chemistry", laureates = listOf(laureate169), id = "1910_Chemistry"),
            NobelPrize(year = "1910", category = "Literature", laureates = listOf(laureate580), id = "1910_Literature"),
            NobelPrize(year = "1910", category = "Peace", laureates = listOf(laureate477), id = "1910_Peace"),
            NobelPrize(year = "1910", category = "Physics", laureates = listOf(laureate15), id = "1910_Physics"),
            NobelPrize(year = "1910", category = "Physiology or Medicine", laureates = listOf(laureate304), id = "1910_Physiology or Medicine"),
        )
    }

    override fun getAll(): List<NobelPrize> = prizes

    override fun findPrize(year: String, category: String): NobelPrize? =
        prizes.firstOrNull { it.year == year && it.category.equals(category, ignoreCase = true) }

    override fun findLaureates(
        year: String,
        category: String
    ): List<Laureate>? {
        return findPrize(year, category)?.laureates
    }


}