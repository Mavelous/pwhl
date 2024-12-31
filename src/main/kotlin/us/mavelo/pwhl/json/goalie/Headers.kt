package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.goalie.headers.*


@Serializable
data class Headers(

		@SerialName("rank") var rank: Rank? = Rank(),
		@SerialName("rookie") var rookie: Rookie? = Rookie(),
		@SerialName("active") var active: Active? = Active(),
		@SerialName("name") var name: Name? = Name(),
		@SerialName("team_code") var teamCode: TeamCode? = TeamCode(),
		@SerialName("games_played") var gamesPlayed: GamesPlayed? = GamesPlayed(),
		@SerialName("minutes_played") var minutesPlayed: MinutesPlayed? = MinutesPlayed(),
		@SerialName("goals_against") var goalsAgainst: GoalsAgainst? = GoalsAgainst(),
		@SerialName("shutouts") var shutouts: Shutouts? = Shutouts(),
		@SerialName("goals_against_average") var goalsAgainstAverage: GoalsAgainstAverage? = GoalsAgainstAverage(),
		@SerialName("wins") var wins: Wins? = Wins(),
		@SerialName("losses") var losses: Losses? = Losses(),
		@SerialName("ot_losses") var otLosses: OtLosses? = OtLosses(),
		@SerialName("shootout_losses") var shootoutLosses: ShootoutLosses? = ShootoutLosses(),
		@SerialName("saves") var saves: Saves? = Saves(),
		@SerialName("shots") var shots: Shots? = Shots(),
		@SerialName("save_percentage") var savePercentage: SavePercentage? = SavePercentage(),
		@SerialName("player_id") var playerId: PlayerId? = PlayerId(),
		@SerialName("shootout_attempts") var shootoutAttempts: ShootoutAttempts? = ShootoutAttempts(),
		@SerialName("shootout_goals_against") var shootoutGoalsAgainst: ShootoutGoalsAgainst? = ShootoutGoalsAgainst(),
		@SerialName("shootout_percentage") var shootoutPercentage: ShootoutPercentage? = ShootoutPercentage(),
		@SerialName("goals") var goals: Goals? = Goals(),
		@SerialName("assists") var assists: Assists? = Assists(),
		@SerialName("penalty_minutes") var penaltyMinutes: PenaltyMinutes? = PenaltyMinutes()

)
