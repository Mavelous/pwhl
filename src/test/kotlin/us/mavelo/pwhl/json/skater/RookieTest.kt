package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RookieTest {
	@Test
	fun isSerializable() {
		assertEquals(1, Rookie::class.annotations.size)
		assertNotNull(Rookie::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructRookie() {
		val json: String = """{ "rookie": "0" }"""

		val rookie: Rookie = Json.decodeFromString<Rookie>(json)

		assertEquals("0", rookie.rookie)
	}
	@Test
	fun canConstructRookieFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/rookie.json")?.readText(Charsets.UTF_8) ?: ""

		val rookie: Rookie = Json.decodeFromString<Rookie>(jsonContent)

		assertEquals("0", rookie.rookie)
	}
}
