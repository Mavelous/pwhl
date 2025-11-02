package us.mavelo.pwhl.json.skater

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertNotEquals
import kotlin.text.Charsets.UTF_8

@kotlinx.serialization.ExperimentalSerializationApi
class RookieTest {
	private val json = Json { encodeDefaults = true; explicitNulls = true }

	@Test
	fun isSerializable() {
		assertEquals(1, Rookie::class.annotations.size)
		assertNotNull(Rookie::class.annotations.find { it is Serializable })
	}

	@Test
	fun defaultConstructorHasNull() {
		val rookie = Rookie()

		assertNull(rookie.rookie)
	}

	@Test
	fun canConstructRookieFromJsonString() {
		val jsonString = """{ "rookie": "0" }"""

		val rookie: Rookie = json.decodeFromString(jsonString)

		assertEquals("0", rookie.rookie)
	}

	@Test
	fun canConstructRookieFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/rookie.json")?.readText(UTF_8) ?: ""

		val rookie: Rookie = json.decodeFromString<Rookie>(jsonContent)

		assertEquals("0", rookie.rookie)
	}

	@Test
	fun deserializeMissingFieldProducesNull() {
		val rookie: Rookie = json.decodeFromString("{}")

		assertNull(rookie.rookie)
	}

	@Test
	fun deserializeNullFieldProducesNull() {
		val rookie: Rookie = json.decodeFromString("""{ "rookie": null }""")

		assertNull(rookie.rookie)
	}

	@Test
	fun serializeAndDeserializeRoundTrip() {
		val original = Rookie("1")
		val payload = json.encodeToString(original)
		val restored: Rookie = json.decodeFromString(payload)

		assertEquals(original, restored)
	}

	@Test
	fun serializeIncludesFieldWhenNotNull() {
		val payload = json.encodeToString(Rookie("1"))

		assertTrue(payload.contains("\"rookie\":\"1\""))
	}

	@Test
	fun copyAndEqualsBehavior() {
		val a = Rookie("a")
		val b = a.copy(rookie = "b")
		val aCopy = a.copy()

		assertNotEquals(a, b)
		assertEquals(a, aCopy)
	}
}
