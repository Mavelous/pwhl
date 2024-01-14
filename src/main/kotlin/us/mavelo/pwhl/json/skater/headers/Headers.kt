package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headers (

		var rank                     : us.mavelo.pwhl.json.skater.headers.Rank?                  = us.mavelo.pwhl.json.skater.headers.Rank(),
		var rookie                   : us.mavelo.pwhl.json.skater.headers.Rookie?                = us.mavelo.pwhl.json.skater.headers.Rookie(),
		var active                   : us.mavelo.pwhl.json.skater.headers.Active?                = us.mavelo.pwhl.json.skater.headers.Active(),
		@SerialName("name") var name : us.mavelo.pwhl.json.skater.headers.HeaderName?            = us.mavelo.pwhl.json.skater.headers.HeaderName(),
		var position                 : us.mavelo.pwhl.json.skater.headers.Position?              = us.mavelo.pwhl.json.skater.headers.Position(),
		@SerialName("team_code") var teamCode                 : us.mavelo.pwhl.json.skater.headers.TeamCode?              = us.mavelo.pwhl.json.skater.headers.TeamCode(),
		@SerialName("games_played") var gamesPlayed              : us.mavelo.pwhl.json.skater.headers.GamesPlayed?           = us.mavelo.pwhl.json.skater.headers.GamesPlayed(),
		var goals                    : us.mavelo.pwhl.json.skater.headers.Goals?                 = us.mavelo.pwhl.json.skater.headers.Goals(),
		var assists                  : us.mavelo.pwhl.json.skater.headers.Assists?               = us.mavelo.pwhl.json.skater.headers.Assists(),
		var points                   : us.mavelo.pwhl.json.skater.headers.Points?                = us.mavelo.pwhl.json.skater.headers.Points(),
		@SerialName("plus_minus") var plusMinus                : us.mavelo.pwhl.json.skater.headers.PlusMinus?             = us.mavelo.pwhl.json.skater.headers.PlusMinus(),
		@SerialName("penalty_minutes") var penaltyMinutes           : us.mavelo.pwhl.json.skater.headers.PenaltyMinutes?        = us.mavelo.pwhl.json.skater.headers.PenaltyMinutes(),
		@SerialName("power_play_goals") var powerPlayGoals           : us.mavelo.pwhl.json.skater.headers.PowerPlayGoals?        = us.mavelo.pwhl.json.skater.headers.PowerPlayGoals(),
		@SerialName("power_play_assists") var powerPlayAssists         : us.mavelo.pwhl.json.skater.headers.PowerPlayAssists?      = us.mavelo.pwhl.json.skater.headers.PowerPlayAssists(),
		@SerialName("short_handed_goals") var shortHandedGoals         : us.mavelo.pwhl.json.skater.headers.ShortHandedGoals?      = us.mavelo.pwhl.json.skater.headers.ShortHandedGoals(),
		@SerialName("short_handed_assists") var shortHandedAssists       : us.mavelo.pwhl.json.skater.headers.ShortHandedAssists?    = us.mavelo.pwhl.json.skater.headers.ShortHandedAssists(),
		@SerialName("points_per_game") var pointsPerGame            : us.mavelo.pwhl.json.skater.headers.PointsPerGame?         = us.mavelo.pwhl.json.skater.headers.PointsPerGame(),
		@SerialName("faceoff_wins") var faceoffWins              : us.mavelo.pwhl.json.skater.headers.FaceoffWins?           = us.mavelo.pwhl.json.skater.headers.FaceoffWins(),
		@SerialName("faceoff_attempts") var faceoffAttempts          : us.mavelo.pwhl.json.skater.headers.FaceoffAttempts?       = us.mavelo.pwhl.json.skater.headers.FaceoffAttempts(),
		@SerialName("faceoff_pct") var faceoffPct               : us.mavelo.pwhl.json.skater.headers.FaceoffPct?            = us.mavelo.pwhl.json.skater.headers.FaceoffPct(),
		@SerialName("penalty_minutes_per_game") var penaltyMinutesPerGame    : us.mavelo.pwhl.json.skater.headers.PenaltyMinutesPerGame? = us.mavelo.pwhl.json.skater.headers.PenaltyMinutesPerGame(),
		var shots                    : us.mavelo.pwhl.json.skater.headers.Shots?                 = us.mavelo.pwhl.json.skater.headers.Shots(),
		@SerialName("shooting_percentage") var shootingPercentage       : us.mavelo.pwhl.json.skater.headers.ShootingPercentage?    = us.mavelo.pwhl.json.skater.headers.ShootingPercentage(),
		@SerialName("shootout_goals") var shootoutGoals            : us.mavelo.pwhl.json.skater.headers.ShootoutGoals?         = us.mavelo.pwhl.json.skater.headers.ShootoutGoals(),
		@SerialName("shootout_attempts") var shootoutAttempts         : us.mavelo.pwhl.json.skater.headers.ShootoutAttempts?      = us.mavelo.pwhl.json.skater.headers.ShootoutAttempts(),
		@SerialName("shootout_winning_goals") var shootoutWinningGoals     : us.mavelo.pwhl.json.skater.headers.ShootoutWinningGoals?  = us.mavelo.pwhl.json.skater.headers.ShootoutWinningGoals(),
		@SerialName("shootout_percentage") var shootoutPercentage       : us.mavelo.pwhl.json.skater.headers.ShootoutPercentage?    = us.mavelo.pwhl.json.skater.headers.ShootoutPercentage(),
		@SerialName("player_id") var playerId                 : us.mavelo.pwhl.json.skater.headers.PlayerId?              = us.mavelo.pwhl.json.skater.headers.PlayerId()

)
