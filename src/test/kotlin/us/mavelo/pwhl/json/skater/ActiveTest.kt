package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.text.Charsets.UTF_8

class ActiveTest {
	@Test
	fun isSerializable() {
		assertEquals(1, Active::class.annotations.size)
		assertNotNull(Active::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructActive() {
		val json = """{
	"active": "1"
}"""
		val active: Active = decodeFromString(json)

		assertEquals("1", active.active)
	}

	@Test
	fun canConstructActiveFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/active.json")?.readText(UTF_8) ?: ""

		val active: Active = decodeFromString(jsonContent)

		assertEquals("1", active.active)
	}
}
