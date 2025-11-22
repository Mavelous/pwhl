package us.mavelo

import us.mavelo.pwhl.Team
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {

	@Test
	fun getTeamsFromArgs_withNoArgs_returnsAllTeams() {
		val teams = getTeamsFromArgs(arrayOf())
		assertEquals(8, teams.size)
	}

	@Test
	fun getTeamsFromArgs_withArgs_returnsTeams() {
		val teams = getTeamsFromArgs(arrayOf("MIN", "VAN", "BOS"))
		assertEquals(3, teams.size)
		assertEquals(Team.BOSTON, teams[0])
		assertEquals(Team.MINNESOTA, teams[1])
		assertEquals(Team.VANCOUVER, teams[2])
	}

	@Test
	fun getTeamFromArgs_withInvalidArg_returnsEmptyList() {
		val teams = getTeamsFromArgs(arrayOf("ABC", "DEF"))
		assertEquals(0, teams.size)
	}

	@Test
	fun getTeamFromArgs_withSomeInvalidArgs_returnsValidTeamsOnly() {
		val teams = getTeamsFromArgs(arrayOf("MIN", "ABC", "SEA", "DEF"))
		assertEquals(2, teams.size)
		assertEquals(Team.MINNESOTA, teams[0])
		assertEquals(Team.SEATTLE, teams[1])
	}

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
