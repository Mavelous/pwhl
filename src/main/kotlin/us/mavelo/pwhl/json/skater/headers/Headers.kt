package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headers(

		var rank: Rank? = Rank(),
		var rookie: Rookie? = Rookie(),
		var active: Active? = Active(),
		@SerialName("name") var name: HeaderName? = HeaderName(),
		var position: Position? = Position(),
		@SerialName("team_code") var teamCode: TeamCode? = TeamCode(),
		@SerialName("games_played") var gamesPlayed: GamesPlayed? = GamesPlayed(),
		var goals: Goals? = Goals(),
		var assists: Assists? = Assists(),
		var points: Points? = Points(),
		@SerialName("plus_minus") var plusMinus: PlusMinus? = PlusMinus(),
		@SerialName("penalty_minutes") var penaltyMinutes: PenaltyMinutes? = PenaltyMinutes(),
		@SerialName("power_play_goals") var powerPlayGoals: PowerPlayGoals? = PowerPlayGoals(),
		@SerialName("power_play_assists") var powerPlayAssists: PowerPlayAssists? = PowerPlayAssists(),
		@SerialName("short_handed_goals") var shortHandedGoals: ShortHandedGoals? = ShortHandedGoals(),
		@SerialName("short_handed_assists") var shortHandedAssists: ShortHandedAssists? = ShortHandedAssists(),
		@SerialName("points_per_game") var pointsPerGame: PointsPerGame? = PointsPerGame(),
		@SerialName("faceoff_wins") var faceoffWins: FaceoffWins? = FaceoffWins(),
		@SerialName("faceoff_attempts") var faceoffAttempts: FaceoffAttempts? = FaceoffAttempts(),
		@SerialName("faceoff_pct") var faceoffPct: FaceoffPct? = FaceoffPct(),
		@SerialName("penalty_minutes_per_game") var penaltyMinutesPerGame: PenaltyMinutesPerGame? = PenaltyMinutesPerGame(),
		var shots: Shots? = Shots(),
		@SerialName("shooting_percentage") var shootingPercentage: ShootingPercentage? = ShootingPercentage(),
		@SerialName("shootout_goals") var shootoutGoals: ShootoutGoals? = ShootoutGoals(),
		@SerialName("shootout_attempts") var shootoutAttempts: ShootoutAttempts? = ShootoutAttempts(),
		@SerialName("shootout_winning_goals") var shootoutWinningGoals: ShootoutWinningGoals? = ShootoutWinningGoals(),
		@SerialName("shootout_percentage") var shootoutPercentage: ShootoutPercentage? = ShootoutPercentage(),
		@SerialName("ice_time_minutes_seconds") var iceTimeMinutesSeconds: IceTimeMinutesSeconds? = IceTimeMinutesSeconds(),
		var hits: Hits? = Hits(),
		@SerialName("player_id") var playerId: PlayerId? = PlayerId()

)
