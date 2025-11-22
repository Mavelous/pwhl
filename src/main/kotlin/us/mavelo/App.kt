package us.mavelo

import kotlinx.serialization.json.Json
import us.mavelo.pwhl.Team
import us.mavelo.pwhl.json.goalie.GoalieSections
import us.mavelo.pwhl.json.skater.PrintableSkaterStats
import us.mavelo.pwhl.json.skater.SkaterSections
import us.mavelo.pwhl.json.skater.headers.Position
import java.lang.Integer.valueOf
import java.net.URI
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
	val url = URI("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=8&team=${team.teamNum}&position=skaters&statsType=standard&sort=points&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1").toURL()
	val jsonFromUrl = url.readText().drop(2).dropLast(2)
	val format = Json { isLenient = true }
	val sections: SkaterSections = format.decodeFromString<SkaterSections>(jsonFromUrl)
	var skaterStats: ArrayList<PrintableSkaterStats> = arrayListOf()

	sections.sections[0].data.forEach { it ->
		if (it.teams != null && it.teams!!.isNotEmpty()) {
			if (it.row!!.position != Position.GOALIE) {
				if (it.teams!!.find { player -> player.active == "1" } != null) {
					val teamStats = it.teams!!.find { player -> player.active == "1" }!!
					skaterStats.add(PrintableSkaterStats(teamStats, it.row!!.name!!))
				}
			}
		} else {
			if (it.row!!.position != Position.GOALIE && it.row!!.active == "1") {
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
	! scope="col" | Player
	! scope="col" | {{abbr|GP|Games played}}
	! scope="col" | {{abbr|G|Goals}}
	! scope="col" | {{abbr|A|Assists}}
	! scope="col" | {{abbr|Pts|Points}}
	! scope="col" | {{abbr|SOG|Shots on Goal}}
	! scope="col" data-sort-type="number" | {{abbr|+/−|Plus/Minus}}
	! scope="col" | {{abbr|PIM|Penalty minutes}}
""".trimIndent())

	skaterStats.forEach { it ->
		println("|-")
		println("! scope=\"row\" style=\"text-align:left;\" | [[${getWikiName(it.name)}]]")
		print("| ${it.gamesPlayed} || ${it.goals} || ${it.assists} || ${it.points} || ${it.shots} || ")
		if (it.plusMinus!!.toInt() > 0) {
			print("{{sort|${it.plusMinus}|+${it.plusMinus}}}")
		} else if (it.plusMinus.toInt() < 0) {
			print("{{sort|${it.plusMinus}|${it.plusMinus.replace("-", "–")}}}")
		} else {
			print("{{sort|${it.plusMinus}|${it.plusMinus}}}")
		}
		println(" || ${it.penaltyMinutes}")
	}
	println("|}")
}

fun printGoalieStats(team: Team) {
	val tableHeader = """
	{| class="wikitable sortable" style="text-align:center"
	|-
	|+ colspan="16" style="background:#fff; border-top:${team.topColor} 5px solid; border-bottom:${team.bottomColor} 5px solid;"|Regular season<ref name="${team.toString().lowercase()}-goalie-stats" />
	|-
	! scope="col" | Player
	! scope="col" | {{abbr|GP|Games played}}
	! scope="col" | {{abbr|TOI|Time on ice}}
	! scope="col" | {{abbr|W|Win}}
	! scope="col" | {{abbr|L|Loss}}
	! scope="col" | {{abbr|OT|Overtime losses}}
	! scope="col" | {{abbr|SOL|Shootout losses}}
	! scope="col" | {{abbr|GA|Goals against}}
	! scope="col" | {{abbr|GAA|Goals against average}}
	! scope="col" | {{abbr|SA|Shots against}}
	! scope="col" | {{abbr|SV%|Save percentage}}
	! scope="col" | {{abbr|SO|Shutouts}}
	! scope="col" | {{abbr|G|Goals}}
	! scope="col" | {{abbr|A|Assists}}
	! scope="col" | {{abbr|PIM|Penalty minutes}}
""".trimIndent()

	val url = URI("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=8&team=${team.teamNum}&position=goalies&statsType=standard&sort=gp&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1").toURL()
	val text = url.readText()
			.drop(2)
			.dropLast(2)
			.replace("\"prop\":[]", "\"prop\":{}")
			.replace("\"minutes_played\":(\\d+)".toRegex(), "\"minutes_played\": \"$1\"")
			.replace("\"shutouts\":(\\d+)".toRegex(), "\"shutouts\": \"$1\"")
	val format = Json { isLenient = true }
	val sections: GoalieSections = format.decodeFromString<GoalieSections>(text)
	val namesNotToPrint = arrayOf(
			"Empty Net ",
			"Totals "
	)
	println(tableHeader)

	sections.sections[0].data.forEach {
		val goalie = it.row!!

		if (goalie.name !in namesNotToPrint && goalie.active == "1") {
			println("|-")
			println("! scope=\"row\" style=\"text-align:left;\" | [[${getWikiName(goalie.name)}]]")
			println("| ${goalie.gamesPlayed} || ${goalie.minutesPlayed} || ${goalie.wins} || ${goalie.losses} || ${goalie.otLosses} || ${goalie.safeShootoutLosses} || ${goalie.goalsAgainst} || ${goalie.goalsAgainstAverage} || ${goalie.shots} || ${goalie.savePercentage} || ${goalie.shutouts} || ${goalie.goals} || ${goalie.assists} || ${goalie.penaltyMinutes}")
		}
	}

	println("|}")
}

fun getWikiName(name: String?): String? {
	val dabNames = arrayOf(
			"Abby Cook",
			"Brittany Howard",
			"Claire Thompson",
			"Emily Brown",
			"Emily Clark",
			"Hannah Miller",
			"Kateřina Mrázová",
			"Lucy Morgan",
			"Maggie Flaherty",
			"Maureen Murphy",
			"Sammy Davis",
			"Taylor Baker",
			"Taylor House",
	)
	if (name in dabNames) {
		return "$name (ice hockey)|$name"
	}

	val accentedNames = mapOf(
		"Chloe Aurard" to "Chloé Aurard",
		"Abby Boreen" to "Abigail Boreen",
		"Clair Degeorge" to "Clair DeGeorge",
		"Daniela Pejsova" to "Daniela Pejšová",
		"Elizabeth Giguère" to "Élizabeth Giguère",
		"Jessica Digirolamo" to "Jessica DiGirolamo",
		"Klara Hymlarova" to "Klára Hymlárová",
		"Kelly Ann Nadeau" to "Kelly-Ann Nadeau",
		"Maja Nylen Persson" to "Maja Nylén Persson",
		"Klara Peslarova" to "Klára Peslarová",
	)
	if (name in accentedNames) {
		return accentedNames[name]
	}

	return name
}
