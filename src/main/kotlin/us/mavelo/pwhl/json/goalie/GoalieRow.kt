package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoalieRow(

		@SerialName("player_id") var playerId: String? = null,
		@SerialName("rookie") var rookie: String? = null,
		@SerialName("name") var name: String? = null,
		@SerialName("active") var active: String? = null,
		@SerialName("team_code") var teamCode: String? = null,
		@SerialName("games_played") var gamesPlayed: String? = null,
		@SerialName("minutes_played") var minutesPlayed: String? = null,
		@SerialName("saves") var saves: String? = null,
		@SerialName("shots") var shots: String? = null,
		@SerialName("save_percentage") var savePercentage: String? = null,
		@SerialName("goals_against") var goalsAgainst: String? = null,
		@SerialName("shutouts") var shutouts: String? = null,
		@SerialName("wins") var wins: String? = null,
		@SerialName("losses") var losses: String? = null,
		@SerialName("ot_losses") var otLosses: String? = null,
		@SerialName("shootout_losses") var shootoutLosses: String? = null,
		@SerialName("shootout_goals_against") var shootoutGoalsAgainst: String? = null,
		@SerialName("shootout_attempts") var shootoutAttempts: String? = null,
		@SerialName("goals") var goals: String? = null,
		@SerialName("assists") var assists: String? = null,
		@SerialName("penalty_minutes") var penaltyMinutes: String? = null,
		@SerialName("shootout_percentage") var shootoutPercentage: String? = null,
		@SerialName("goals_against_average") var goalsAgainstAverage: String? = null,
		@SerialName("rank") var rank: Int? = null

)
