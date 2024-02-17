package us.mavelo.pwhl.json.skater

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
		@SerialName("player_id") var playerId: String? = null,
		var active: String? = null,
		@SerialName("team_code") var teamCode: String? = null,
		@SerialName("games_played") var gamesPlayed: String? = null,
		var goals: String? = null,
		var assists: String? = null,
		var points: String? = null,
		@SerialName("penalty_minutes") var penaltyMinutes: String? = null,
		@SerialName("plus_minus") var plusMinus: String? = null,
		@SerialName("power_play_goals") var powerPlayGoals: String? = null,
		@SerialName("power_play_assists") var powerPlayAssists: String? = null,
		@SerialName("short_handed_goals") var shortHandedGoals: String? = null,
		@SerialName("short_handed_assists") var shortHandedAssists: String? = null,
		var position: String? = null,
		var shots: String? = null,
		@SerialName("shooting_percentage") var shootingPercentage: String? = null,
		@SerialName("shootout_goals") var shootoutGoals: String? = null,
		@SerialName("shootout_winning_goals") var shootoutWinningGoals: String? = null,
		@SerialName("shootout_attempts") var shootoutAttempts: String? = null,
		@SerialName("shootout_percentage") var shootoutPercentage: String? = null,
		@SerialName("points_per_game") var pointsPerGame: String? = null,
		@SerialName("penalty_minutes_per_game") var penaltyMinutesPerGame: String? = null,
		@SerialName("faceoff_attempts") var faceoffAttempts: String? = null,
		@SerialName("faceoff_wins") var faceoffWins: String? = null,
		@SerialName("faceoff_pct") var faceoffPct: String? = null,
		var name: String? = null,
)
