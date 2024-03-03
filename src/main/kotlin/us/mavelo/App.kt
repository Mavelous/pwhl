package us.mavelo

import kotlinx.serialization.json.Json.Default.decodeFromString
import us.mavelo.pwhl.Team
import us.mavelo.pwhl.json.goalie.GoalieSections
import us.mavelo.pwhl.json.skater.PrintableSkaterStats
import us.mavelo.pwhl.json.skater.SkaterSections
import java.lang.Integer.*
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class App {
	val greeting: String
		get() {
			return "Hello World!"
		}
}

fun main() {
	val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
	val currentDate = LocalDateTime.now().format(formatter)
	for (team in Team.entries) {
		println("****************")
		println("[${team}]")
		println("****************")

		println("""==Player statistics==

{{Updated|${currentDate}|}}<ref name="${team.toString().lowercase()}-stats" />

===Skaters===
""")
		val skaterStats: ArrayList<PrintableSkaterStats> = collectSortedSkaters(team)
		printSkaterStats(team, skaterStats)
		println("\n===Goaltenders===\n")
		printGoalieStats(team)
		println("\n")
	}
}

private fun collectSortedSkaters(team: Team): ArrayList<PrintableSkaterStats> {
	val url = URL("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=1&team=${team.teamNum}&position=skaters&statsType=standard&sort=points&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1")
	val text = url.readText().drop(2).dropLast(2)
	val sections: SkaterSections = decodeFromString<SkaterSections>(text)
	var skaterStats: ArrayList<PrintableSkaterStats> = arrayListOf()

	sections.sections[0].data.forEach { it ->
		if (it.teams != null && it.teams!!.isNotEmpty()) {
			if (it.row!!.position != "G") {
				if (it.teams!!.find { player -> player.active == "1" } != null) {
					val teamStats = it.teams!!.find { player -> player.active == "1" }!!
					skaterStats.add(PrintableSkaterStats(teamStats, it.row!!.name!!))
				}
			}
		} else {
			if (it.row!!.position != "G" && it.row!!.active == "1") {
				skaterStats.add(PrintableSkaterStats(it.row!!))
			}
		}
	}

	skaterStats.sortByDescending { it -> valueOf(it.goals) }
	skaterStats.sortByDescending { it -> valueOf(it.points) }

	return skaterStats
}

private fun printSkaterStats(team: Team, skaterStats: ArrayList<PrintableSkaterStats>) {
	println("""
	{| class="wikitable sortable" style="text-align:center;"
	|+ style="background:#fff; border-top:${team.topColor} 5px solid; border-bottom:${team.bottomColor} 5px solid;"|Regular season<ref name="${team.toString().lowercase()}-stats" />
	|-
	! Player !! {{abbr|GP|Games played}} !! {{abbr|G|Goals}} !! {{abbr|A|Assists}} !! {{abbr|Pts|Points}} !! {{abbr|SOG|Shots on Goal}} !! {{abbr|+/−|Plus/Minus}} !! {{abbr|PIM|Penalty minutes}}
""".trimIndent())

	skaterStats.forEach { it ->
		println("|-")
		print("| style=\"text-align:left;\"|[[${getWikiName(it.name)}]] || ${it.gamesPlayed} || ${it.goals} || ${it.assists} || ${it.points} || ${it.shots} || ")
		if (it.plusMinus!!.toInt() > 0) {
			print("+")
		}
		println("${it.plusMinus} || ${it.penaltyMinutes}")
	}
	println("|}")
}

fun printGoalieStats(team: Team) {
	val tableHeader = """
	{| class="wikitable sortable" style="text-align:center"
	|-
	|+ colspan="16" style="background:#fff; border-top:${team.topColor} 5px solid; border-bottom:${team.bottomColor} 5px solid;"|Regular season<ref name="${team.toString().lowercase()}-goalie-stats" />
	|-
	! Player !! {{abbr|GP|Games played}} !! {{abbr|TOI|Time on ice}} !! {{abbr|W|Win}} !! {{abbr|L|Loss}} !! {{abbr|OT|Overtime loss}} !! {{abbr|GA|Goals against}} !! {{abbr|GAA|Goals against average}} !! {{abbr|SA|Shots against}} !! {{abbr|SV%|Save percentage}} !! {{abbr|SO|Shutouts}} !! {{abbr|G|Goals}} !! {{abbr|A|Assists}} !! {{abbr|PIM|Penalty minutes}}
""".trimIndent()

	val url = URL("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=1&team=${team.teamNum}&position=goalies&statsType=standard&sort=gp&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1")
	val text = url.readText()
			.drop(2)
			.dropLast(2)
			.replace("\"prop\":[]", "\"prop\":{}")
			.replace("\"minutes_played\":(\\d+)".toRegex(), "\"minutes_played\": \"$1\"")
			.replace("\"shutouts\":(\\d+)".toRegex(), "\"shutouts\": \"$1\"")

	val sections: GoalieSections = decodeFromString<GoalieSections>(text)
	val namesNotToPrint = arrayOf(
			"Empty Net ",
			"Totals "
	)
	println(tableHeader)

	sections.sections[0].data.forEach {
		val goalie = it.row!!

		if (goalie.name !in namesNotToPrint) {
			println("|-")
			println("| style=\"text-align:left;\"|[[${goalie.name}]] || ${goalie.gamesPlayed} || ${goalie.minutesPlayed} || ${goalie.wins} || ${goalie.losses} || ${goalie.otLosses} || ${goalie.goalsAgainst} || ${goalie.goalsAgainstAverage} || ${goalie.shots} || ${goalie.savePercentage} || ${goalie.shutouts} || ${goalie.goals} || ${goalie.assists} || ${goalie.penaltyMinutes}")
		}
	}

	println("|}")
}

fun getWikiName(name: String?): String? {
	val dabNames = arrayOf(
			"Abby Cook",
			"Maggie Flaherty",
			"Hilary Knight",
			"Sammy Davis",
			"Emily Clark",
			"Kateřina Mrázová",
			"Taylor Baker",
			"Brittany Howard",
			"Hannah Miller",
			"Emily Brown",
			"Maureen Murphy",
	)
	if (name in dabNames) {
		return "$name (ice hockey)|$name"
	}

	return name
}
