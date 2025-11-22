package us.mavelo.pwhl

class GoalieUrl: StatsUrl() {
	override fun getPosition(): String {
		return "goalies"
	}

	override fun getSort(): String {
		return "gp"
	}
}
