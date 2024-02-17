package us.mavelo.pwhl.json.skater

data class PrintableSkaterStats(
		val name: String? = null,
		val gamesPlayed: String? = null,
		val goals: String? = null,
		val assists: String? = null,
		val points: String? = null,
		val shots: String? = null,
		val plusMinus: String? = null,
		val penaltyMinutes: String? = null,
) {
	constructor(data: SkaterRow) :
			this(data.name, data.gamesPlayed, data.goals, data.assists, data.points, data.shots, data.plusMinus, data.penaltyMinutes)

	constructor(data: Team, name: String) :
			this(name, data.gamesPlayed, data.goals, data.assists, data.points, data.shots, data.plusMinus, data.penaltyMinutes)
}
