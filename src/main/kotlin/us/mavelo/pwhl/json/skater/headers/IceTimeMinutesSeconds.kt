package us.mavelo.pwhl.json.skater.headers

import kotlinx.serialization.Serializable

@Serializable
data class IceTimeMinutesSeconds(
	var properties: Properties? = Properties()
)
