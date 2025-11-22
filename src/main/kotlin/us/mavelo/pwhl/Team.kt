package us.mavelo.pwhl

enum class Team(val teamNum: Int, val shortName: String, val topColor: String = "#fff", val bottomColor: String = "#000") {
	BOSTON(1, "BOS", "#164E36", "#C3C6CC"),
	MINNESOTA(2, "MIN", "#250E62", "#A77BCA"),
	MONTREAL(3, "MTL", "#651517"),
	NEW_YORK(4, "NYC", "#18B196", "#151E38"),
	OTTAWA(5, "OTT", "#BC1F1E", "#383334"),
	TORONTO(6, "TOR", "#1A75BD"),
	VANCOUVER(9, "VAN", "#0F4777", "#EEE9D8"),
	SEATTLE(8, "SEA", "#0A5258", "#EEE9D8");

	companion object {
		fun fromShortName(shortName: String): Team? {
			return values().find { it.shortName == shortName }
		}
	}
}
