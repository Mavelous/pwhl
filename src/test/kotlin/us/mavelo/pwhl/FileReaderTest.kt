package us.mavelo.pwhl

import kotlinx.serialization.json.Json
import us.mavelo.pwhl.json.Sections
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

class FileReaderTest {
	@Test
	fun strippingCharactersFromOriginalResultsInSameContent() {
		val originalContent: String = this::class.java.getResource("/data.original.txt")?.readText(UTF_8) ?: ""
		val jsonContent: String = this::class.java.getResource("/data.json")?.readText(UTF_8) ?: ""

		assertEquals((originalContent.length.minus(4)), jsonContent.length)

		var modifiedOriginal = originalContent.drop(2).dropLast(3)
		modifiedOriginal = modifiedOriginal.plus("\n")

		assertEquals(modifiedOriginal.length, jsonContent.length)
		assertEquals(modifiedOriginal, jsonContent)
	}

	@Test
	fun canParseJsonAndTurnItIntoObjects() {
		val jsonContent: String = this::class.java.getResource("/data.formatted.json")?.readText(UTF_8) ?: ""

		assertTrue(jsonContent.length > 1)

		val sections: Sections = Json.decodeFromString<Sections>(jsonContent)

		assertNotNull(sections)

		sections.sections[0].data.forEach { it ->
			println("Name: [${it.row!!.name}], goals: [${it.row!!.goals}]")
		}
	}
}
