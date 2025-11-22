package us.mavelo.pwhl

abstract class StatsUrl {
	val baseUrl: String = "https://lscluster.hockeytech.com/feed/index.php"
	val feed: String = "statviewfeed"
	val view: String = "players"
	val season: String = "8"
	val team: String = "1"
	val statsType: String = "standard"
	val leagueId: String = "1"
	val lang: String = "en"
	val division: String = "-1"
	val key = "694cfeed58c932ee"
	val clientCode: String = "pwhl"

	fun getStatsUrl(teamNum: Int): String {
		return "$baseUrl?feed=$feed&view=$view&season=$season&team=$teamNum&position=${getPosition()}&statsType=$statsType&sort=${getSort()}&league_id=$leagueId&lang=$lang&division=$division&key=$key&client_code=$clientCode"
	}

	abstract fun getPosition(): String
	abstract fun getSort(): String
}
