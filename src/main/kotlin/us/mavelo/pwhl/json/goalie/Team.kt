package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.goalie.headers.GoalsAgainstAverage

@Serializable
data class Team(
		@SerialName("player_id") var playerId: String? = null,
		var rookie: String? = null,
		var name: String? = null,
		var active: String? = null,
		@SerialName("team_code") var teamCode: String? = null,
		@SerialName("games_played") var gamesPlayed: String? = null,
		@SerialName("minutes_played") var minutesPlayed: String? = null,
		var saves: String? = null,
		var shots: String? = null,
		@SerialName("save_percentage") var savePercentage: String? = null,
		@SerialName("goals_against") var goalsAgainst: String? = null,
		var shutouts: String? = null,
		var wins: String? = null,
		var losses: String? = null,
		@SerialName("ot_losses") var otLosses: String? = null,
		@SerialName("shootout_losses") var shootoutLosses: String? = null,
		@SerialName("shootout_goals_against") var shootoutGoalsAgainst: String? = null,
		@SerialName("shootout_attempts") var shootoutAttempts: String? = null,
		var goals: String? = null,
		var assists: String? = null,
		@SerialName("penalty_minutes") var penaltyMinutes: String? = null,
		@SerialName("shootout_percentage") var shootoutPercentage: String? = null,
		@SerialName("goals_against_average") var goalsAgainstAverage: String? = null,
		var rank: String? = null,
)
