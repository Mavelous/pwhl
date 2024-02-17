package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.text.Charsets.UTF_8


class NameTest {
	@Test
	fun isSerializable() {
		assertEquals(1, Name::class.annotations.size)
		assertNotNull(Name::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructName() {
		val json = """{
	"playerLink": "73",
	"seoName": "Emma Maltais"
}"""

		val name: Name = decodeFromString(json)

		assertEquals("Emma Maltais", name.seoName)
		assertEquals("73", name.playerLink)
	}

	@Test
	fun canConstructNameFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/name.json")?.readText(UTF_8) ?: ""

		val name: Name = decodeFromString<Name>(jsonContent)

		assertEquals("Emma Maltais", name.seoName)
		assertEquals("73", name.playerLink)
	}
}
