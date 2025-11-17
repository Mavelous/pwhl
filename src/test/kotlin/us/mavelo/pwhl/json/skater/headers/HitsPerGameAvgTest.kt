package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

@kotlinx.serialization.ExperimentalSerializationApi
class HitsPerGameAvgTest {
	private val json = Json { encodeDefaults = true; explicitNulls = true }

	@Test
	fun isSerializable() {
		assertEquals(1, HitsPerGameAvg::class.annotations.size)
		assertNotNull(HitsPerGameAvg::class.annotations.find { it is kotlinx.serialization.Serializable })
	}

	@Test
	fun canConstructFromEmptyJson() {
		val obj: HitsPerGameAvg = decodeFromString("{}")
		assertNotNull(obj)
		assertNotNull(obj.properties)
	}

	@Test
	fun canConstructFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/headers/hits_per_game_avg.json")?.readText(UTF_8) ?: ""
		val obj: HitsPerGameAvg = decodeFromString(jsonContent)

		assertNotNull(obj)
		assertNotNull(obj.properties)
	}

	@Test
	fun serializationContainsPropertiesKey() {
		val payload = json.encodeToString(HitsPerGameAvg())

		assertTrue(payload.contains("\"properties\""), "serialized output missing key: properties")
	}

	@Test
	fun serializationIncludesNullWhenPropertiesNull() {
		val inst = HitsPerGameAvg()
		inst.properties = null

		val payload = json.encodeToString(inst)

		assertTrue(payload.contains("\"properties\":null"), "serialized output should include explicit null for properties")
	}
}
