package us.mavelo.pwhl

class SkaterUrl: StatsUrl() {
	override fun getPosition(): String {
		return "skaters"
	}

	override fun getSort(): String {
		return "points"
	}
}
