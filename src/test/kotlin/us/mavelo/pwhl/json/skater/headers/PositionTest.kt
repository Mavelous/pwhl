// kotlin
package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

@kotlinx.serialization.ExperimentalSerializationApi
class PositionTest {
	// allow unknown keys so we can assert properties content in the JSON without requiring exact Properties shape
	private val json = Json { encodeDefaults = true; explicitNulls = true; ignoreUnknownKeys = true }

	@Test
	fun isSerializable() {
		assertEquals(1, Position::class.annotations.size)
		assertNotNull(Position::class.annotations.find { it is kotlinx.serialization.Serializable })
	}

	@Test
	fun canConstructPosition() {
		val raw = "{}"
		val obj: Position = json.decodeFromString(raw)
		assertNotNull(obj.properties)
	}

	@Test
	fun canConstructPositionFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/headers/position.json")?.readText(UTF_8) ?: ""
		val obj: Position = json.decodeFromString(jsonContent)
		assertNotNull(obj.properties)
	}

	@Test
	fun resourcePropertiesContainExpectedFields() {
		val jsonContent: String = this::class.java.getResource("/json/skater/headers/position.json")?.readText(UTF_8) ?: ""
		val parsed = json.parseToJsonElement(jsonContent)
		val props = parsed.jsonObject["properties"]?.jsonObject
		assertNotNull(props)

		// Assertions based on the richer properties in the resource
		assertEquals("G", props["code"]?.jsonPrimitive?.content)
		assertEquals("Goalie", props["displayName"]?.jsonPrimitive?.content)
		assertTrue(props["primary"]?.jsonPrimitive?.boolean == true)
	}

	@Test
	fun serializationUsesSerialNames() {
		val payload = json.encodeToString(Position())
		val expectedKeys = listOf("properties")
		for (key in expectedKeys) {
			kotlin.test.assertTrue(payload.contains("\"$key\""), "serialized output missing key: $key")
		}
	}

	@Test
	fun companionConstant() {
		assertEquals("G", Position.GOALIE)
	}
}
