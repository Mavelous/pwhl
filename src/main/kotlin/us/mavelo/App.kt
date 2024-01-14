package us.mavelo

import kotlinx.serialization.json.Json
import us.mavelo.pwhl.Team
import us.mavelo.pwhl.json.skater.SkaterSections
import us.mavelo.pwhl.json.goalie.GoalieSections
import java.net.URL

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    for (team in Team.entries) {
        println("****************")
        println("[${team}]")
        println("****************")

        printSkaterStats(team)
        println("\n\n")
        printGoalieStats(team)
        println("\n\n")
    }
}

private fun printSkaterStats(team: Team) {
    val tableHeader = """
            {| class="wikitable" style="text-align:center; width: 60em"
            |-
            ! |
            ! rowspan="100" |
            ! colspan="7" style="background:#fff; border-top:${team.topColor} 5px solid; border-bottom:${team.bottomColor} 5px solid;"|Regular season<ref name="${team.toString().lowercase()}-stats" />
            |-
            ! Player !! {{abbr|GP|Games played}} !! {{abbr|G|Goals}} !! {{abbr|A|Assists}} !! {{abbr|Pts|Points}} !! {{abbr|SOG|Shots on Goal}} !! {{abbr|+/−|Plus/Minus}} !! {{abbr|PIM|Penalty minutes}}
        """.trimIndent()

    val url: URL = URL("https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=1&team=${team.teamNum}&position=skaters&statsType=standard&sort=points&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl&league_id=1")

    val text = url.readText().drop(2).dropLast(2)

    val sections: SkaterSections = Json.decodeFromString<SkaterSections>(text)

    println(tableHeader)

    sections.sections[0].data.forEach { it ->
        val player = it.row!!
        if (player.position != "G") {
            println("|-")
            print("| style=\"text-align:left;\"|[[${getWikiName(player.name)}]] || ${player.gamesPlayed} || ${player.goals} || ${player.assists} || ${player.points} || ${player.shots} || ")
            if (player.plusMinus!!.toInt() > 0) {
                print("+")
            }
            println("${player.plusMinus} || ${player.penaltyMinutes}")
        }
    }
    println("|}")
}

fun printGoalieStats(team: Team) {
    val tableHeader = """
        {| class="wikitable sortable" style="text-align:center"
        |-
        |+ colspan="16" style="background:#fff; border-top:#5A288D 5px solid; border-bottom:#000 5px solid;"|Regular season<ref name="minnesota-goalie-stats" />
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

    val sections: GoalieSections = Json.decodeFromString<GoalieSections>(text)
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
            )
    if (name in dabNames) {
        return "$name (ice hockey)|$name"
    }

    return name
}
