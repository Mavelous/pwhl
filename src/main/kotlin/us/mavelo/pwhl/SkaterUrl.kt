package us.mavelo.pwhl

class SkaterUrl(currentSeason: Season) : StatsUrl(currentSeason) {
	override fun getPosition(): String {
		return "skaters"
	}

	override fun getSort(): String {
		return "points"
	}
}
