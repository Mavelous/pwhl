package us.mavelo

import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
	@Test
	fun getWikiName_returnsDabNameFormat() {
		val dabNames = arrayOf(
				"Abby Cook",
				"Brittany Howard",
				"Claire Thompson",
				"Emily Brown",
				"Emily Clark",
				"Hannah Miller",
				"Kateřina Mrázová",
				"Lucy Morgan",
				"Maggie Flaherty",
				"Maureen Murphy",
				"Sammy Davis",
				"Taylor Baker",
				"Taylor House",
		)

		for (name in dabNames) {
			val expected = "$name (ice hockey)|$name"
			assertEquals(expected, getWikiName(name))
		}
	}

	@Test
	fun getWikiName_replacesWithAccentedName() {
		val unaccentedNames = arrayOf(
				"Chloe Aurard",
				"Clair Degeorge",
				"Daniela Pejsova",
				"Elizabeth Giguère",
				"Jessica Digirolamo",
				"Klara Hymlarova",
				"Kelly Ann Nadeau",
				"Maja Nylen Persson",
				"Klara Peslarova",
		)
		val accentedNames = arrayOf(
				"Chloé Aurard",
				"Clair DeGeorge",
				"Daniela Pejšová",
				"Élizabeth Giguère",
				"Jessica DiGirolamo",
				"Klára Hymlárová",
				"Kelly-Ann Nadeau",
				"Maja Nylén Persson",
				"Klára Peslarová",
		)

		for (i in unaccentedNames.indices) {
			assertEquals(accentedNames[i], getWikiName(unaccentedNames[i]))
		}
	}

	@Test
	fun getWikiName_returnsSameNameWhenNotInMaps() {
		val input = "Some Unknown Player"
		assertEquals(input, getWikiName(input))
	}

}
