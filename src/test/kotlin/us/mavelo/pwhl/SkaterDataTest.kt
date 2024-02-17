package us.mavelo.pwhl

import kotlinx.serialization.json.Json.Default.decodeFromString
import org.json.JSONException
import org.json.JSONObject
import us.mavelo.pwhl.json.skater.SkaterRow
import us.mavelo.pwhl.json.skater.SkaterSections
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

class SkaterDataTest {
	@Test
	fun strippingCharactersFromOriginalResultsInSameContent() {
		val originalContent: String = this::class.java.getResource("/skater_data.original.txt")?.readText(UTF_8) ?: ""
		val jsonContent: String = this::class.java.getResource("/skater_data.json")?.readText(UTF_8) ?: ""

		assertEquals((originalContent.length.minus(4)), jsonContent.length)

		var modifiedOriginal = originalContent.drop(2).dropLast(3)
		modifiedOriginal = modifiedOriginal.plus("\n")

		assertEquals(modifiedOriginal.length, jsonContent.length)
		assertEquals(modifiedOriginal, jsonContent)
	}

	@Test
	fun canParseJsonAndTurnItIntoObjects() {
		val jsonContent: String = this::class.java.getResource("/skater_data.formatted.json")?.readText(UTF_8) ?: ""

		assertTrue(jsonContent.length > 1)

		val sections: SkaterSections = decodeFromString<SkaterSections>(jsonContent)

		val player: SkaterRow = sections.sections[0].data[0].row!!
		assertEquals("Emma Maltais", player.name)
		assertEquals("1", player.goals)
	}

	@Test
	fun canReadFromWebpageAndParseToJson() {
		val webUrl = URL("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=1&team=2&position=skaters&statsType=standard&sort=goals&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1")

		var text = webUrl.readText()

		assertNotNull(text)

		text = text.drop(2).dropLast(2)

		val sections: SkaterSections = decodeFromString<SkaterSections>(text)

		assertNotNull(sections)

		val player: SkaterRow = sections.sections[0].data[0].row!!
		assertEquals("Grace Zumwinkle", player.name)
		assertEquals("6", player.goals)
	}
}
