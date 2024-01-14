package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable

@Serializable
data class SkaterSection (

		var title   : String?         = null,
		var headers : us.mavelo.pwhl.json.skater.headers.Headers?        = us.mavelo.pwhl.json.skater.headers.Headers(),
		var data    : ArrayList<Data> = arrayListOf()

)
