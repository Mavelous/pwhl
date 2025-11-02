package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.text.Charsets.UTF_8

class ShotsBlockedByPlayerTest {
	@Test
	fun isSerializable() {
		assertEquals(1, ShotsBlockedByPlayer::class.annotations.size)
		assertNotNull(ShotsBlockedByPlayer::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructShotsBlockedByPlayer() {
		val json = """{"properties": {}}"""
		val obj: ShotsBlockedByPlayer = decodeFromString(json)

		assertNotNull(obj.properties)
	}

	@Test
	fun canConstructShotsBlockedByPlayerFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/headers/shots_blocked_by_player.json")?.readText(UTF_8) ?: ""

		val obj: ShotsBlockedByPlayer = decodeFromString(jsonContent)

		assertNotNull(obj.properties)
	}
}
