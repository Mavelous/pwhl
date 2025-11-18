package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.Serializable

@Serializable
data class Position(
		var properties: Properties? = Properties()
) {
	companion object {
		const val GOALIE = "G"
	}

}
