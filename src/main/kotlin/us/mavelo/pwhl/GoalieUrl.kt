package us.mavelo.pwhl

class GoalieUrl(currentSeason: Season) : StatsUrl(currentSeason) {
	override fun getPosition(): String {
		return "goalies"
	}

	override fun getSort(): String {
		return "gp"
	}
}
