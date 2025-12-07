package us.mavelo.pwhl

enum class Team(val teamNum: Int, val shortName: String, val topColor: String = "#fff", val bottomColor: String = "#000") {
	BOSTON(1, "BOS", "#173F35", "#B5E3D8"),
	MINNESOTA(2, "MIN", "#250E62", "#A77BCA"),
	MONTREAL(3, "MTL", "#862633", "#041E42"),
	NEW_YORK(4, "NYC", "#00BFB3", "#041E42"),
	OTTAWA(5, "OTT", "#A6192E", "#4B4F54"),
	TORONTO(6, "TOR", "#0067B9", "#0C2340"),
	VANCOUVER(9, "VAN", "#0F4777", "#EEE9D8"),
	SEATTLE(8, "SEA", "#0C5256", "#E1DBC9");

	companion object {
		fun fromShortName(shortName: String): Team? {
			return values().find { it.shortName == shortName }
		}
	}
}
