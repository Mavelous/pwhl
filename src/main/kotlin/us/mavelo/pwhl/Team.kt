package us.mavelo.pwhl

enum class Team(val teamNum: Int, val topColor: String = "#fff", val bottomColor: String = "#000") {
	BOSTON(1, "#164E36", "#C3C6CC"),
	MINNESOTA(2, "#5A288D"),
	MONTREAL(3, "#651517"),
	NEW_YORK(4, "#18B196", "#151E38"),
	OTTAWA(5, "#BC1F1E", "#383334"),
	TORONTO(6, "#1A75BD")
}
