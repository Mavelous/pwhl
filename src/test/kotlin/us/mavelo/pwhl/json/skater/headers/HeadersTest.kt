// kotlin
package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.text.Charsets.UTF_8

@kotlinx.serialization.ExperimentalSerializationApi
class HeadersTest {
	private val json = Json { encodeDefaults = true; explicitNulls = true }

	@Test
	fun isSerializable() {
		assertEquals(1, Headers::class.annotations.size)
		assertNotNull(Headers::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructHeaders() {
		val json = "{}"
		val obj: Headers = decodeFromString(json)

		assertNotNull(obj.rank)
		assertNotNull(obj.rookie)
		assertNotNull(obj.active)
		assertNotNull(obj.name)
		assertNotNull(obj.position)
		assertNotNull(obj.teamCode)
		assertNotNull(obj.gamesPlayed)
		assertNotNull(obj.goals)
		assertNotNull(obj.assists)
		assertNotNull(obj.points)
		assertNotNull(obj.plusMinus)
		assertNotNull(obj.penaltyMinutes)
		assertNotNull(obj.powerPlayGoals)
		assertNotNull(obj.powerPlayAssists)
		assertNotNull(obj.shortHandedGoals)
		assertNotNull(obj.shortHandedAssists)
		assertNotNull(obj.pointsPerGame)
		assertNotNull(obj.faceoffWins)
		assertNotNull(obj.faceoffAttempts)
		assertNotNull(obj.faceoffPct)
		assertNotNull(obj.penaltyMinutesPerGame)
		assertNotNull(obj.shots)
		assertNotNull(obj.shotsBlockedByPlayer)
		assertNotNull(obj.shootingPercentage)
		assertNotNull(obj.shootoutGoals)
		assertNotNull(obj.shootoutAttempts)
		assertNotNull(obj.shootoutWinningGoals)
		assertNotNull(obj.shootoutPercentage)
		assertNotNull(obj.iceTimeMinutesSeconds)
		assertNotNull(obj.hits)
		assertNotNull(obj.hitsPerGameAvg)
		assertNotNull(obj.iceTimePerGameAvg)
		assertNotNull(obj.playerId)
	}

	@Test
	fun canConstructHeadersFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/skater/headers/headers.json")?.readText(UTF_8) ?: ""

		val obj: Headers = decodeFromString(jsonContent)

		assertNotNull(obj.rank)
		assertNotNull(obj.rookie)
		assertNotNull(obj.active)
		assertNotNull(obj.name)
		assertNotNull(obj.position)
		assertNotNull(obj.teamCode)
		assertNotNull(obj.gamesPlayed)
		assertNotNull(obj.goals)
		assertNotNull(obj.assists)
		assertNotNull(obj.points)
		assertNotNull(obj.plusMinus)
		assertNotNull(obj.penaltyMinutes)
		assertNotNull(obj.powerPlayGoals)
		assertNotNull(obj.powerPlayAssists)
		assertNotNull(obj.shortHandedGoals)
		assertNotNull(obj.shortHandedAssists)
		assertNotNull(obj.pointsPerGame)
		assertNotNull(obj.faceoffWins)
		assertNotNull(obj.faceoffAttempts)
		assertNotNull(obj.faceoffPct)
		assertNotNull(obj.penaltyMinutesPerGame)
		assertNotNull(obj.shots)
		assertNotNull(obj.shotsBlockedByPlayer)
		assertNotNull(obj.shootingPercentage)
		assertNotNull(obj.shootoutGoals)
		assertNotNull(obj.shootoutAttempts)
		assertNotNull(obj.shootoutWinningGoals)
		assertNotNull(obj.shootoutPercentage)
		assertNotNull(obj.iceTimeMinutesSeconds)
		assertNotNull(obj.hits)
		assertNotNull(obj.hitsPerGameAvg)
		assertNotNull(obj.iceTimePerGameAvg)
		assertNotNull(obj.playerId)
	}

	@Test
	fun serializationUsesSerialNames() {
		val payload = json.encodeToString(Headers())

		val expectedKeys = listOf(
				"rank",
				"rookie",
				"active",
				"name",
				"position",
				"team_code",
				"games_played",
				"goals",
				"assists",
				"points",
				"plus_minus",
				"penalty_minutes",
				"power_play_goals",
				"power_play_assists",
				"short_handed_goals",
				"short_handed_assists",
				"points_per_game",
				"faceoff_wins",
				"faceoff_attempts",
				"faceoff_pct",
				"penalty_minutes_per_game",
				"shots",
				"shots_blocked_by_player",
				"shooting_percentage",
				"shootout_goals",
				"shootout_attempts",
				"shootout_winning_goals",
				"shootout_percentage",
				"ice_time_minutes_seconds",
				"hits",
				"hits_per_game_avg",
				"ice_time_per_game_avg",
				"player_id"
		)

		for (key in expectedKeys) {
			kotlin.test.assertTrue(payload.contains("\"$key\""), "serialized output missing key: $key")
		}
	}
}
