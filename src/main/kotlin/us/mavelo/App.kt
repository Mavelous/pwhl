package us.mavelo

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.*
import kotlinx.serialization.json.Json
import us.mavelo.pwhl.Team
import us.mavelo.pwhl.json.Sections
import java.net.URL

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {

    for (team in Team.entries) {
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
        println("****************")
        println("[${team}]")
        println("****************")

        val text = url.readText().drop(2).dropLast(2)

        val sections: Sections = Json.decodeFromString<Sections>(text)

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
}

fun getWikiName(name: String?): String? {
    var dabNames = arrayOf(
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
