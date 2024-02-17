package us.mavelo.pwhl

import kotlinx.serialization.json.Json.Default.decodeFromString
import us.mavelo.pwhl.json.goalie.GoalieRow
import us.mavelo.pwhl.json.goalie.GoalieSections
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

class GoalieDataTest {
	@Test
	fun strippingCharactersFromOriginalResultsInSameContent() {
		val originalContent: String = this::class.java.getResource("/goalie_data.original.txt")?.readText(UTF_8) ?: ""
		val jsonContent: String = this::class.java.getResource("/goalie_data.json")?.readText(UTF_8) ?: ""

		assertEquals((originalContent.length.minus(4)), jsonContent.length)

		var modifiedOriginal = originalContent.drop(2).dropLast(3)
		modifiedOriginal = modifiedOriginal.plus("\n")

		assertEquals(modifiedOriginal.length, jsonContent.length)
		assertEquals(modifiedOriginal, jsonContent)
	}

	@Test
	fun canParseJsonAndTurnItIntoObjects() {
		val jsonContent: String = this::class.java.getResource("/goalie_data.formatted.json")
				?.readText(UTF_8)
				?.replace("\"prop\": []", "\"prop\": {}")
				?.replace("\"minutes_played\": (\\d+)".toRegex(), "\"minutes_played\": \"$1\"")
				?.replace("\"shutouts\": (\\d+)".toRegex(), "\"shutouts\": \"$1\"")
				?: ""

		assertTrue(jsonContent.length > 1)

		val sections: GoalieSections = decodeFromString<GoalieSections>(jsonContent)

		assertNotNull(sections)
		val player: GoalieRow = sections.sections[0].data[0].row!!
		assertEquals("Maddie Rooney", player.name)
		assertEquals("0.00", player.goalsAgainstAverage)
	}

	@Test
	fun canReadFromWebpageAndParseToJson() {
		val webUrl = URL("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=1&team=2&position=goalies&statsType=standard&sort=goals&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1")

		var text = webUrl.readText()

		assertNotNull(text)

		text = text
				.drop(2)
				.dropLast(2)
				.replace("\"prop\":[]", "\"prop\":{}")
				.replace("\"minutes_played\":(\\d+)".toRegex(), "\"minutes_played\": \"$1\"")
				.replace("\"shutouts\":(\\d+)".toRegex(), "\"shutouts\": \"$1\"")

		val sections: GoalieSections = decodeFromString<GoalieSections>(text)

		assertNotNull(sections)
		val player: GoalieRow = sections.sections[0].data[0].row!!

		assertTrue(player.name in arrayOf("Maddie Rooney", "Nicole Hensley"))
	}
}
