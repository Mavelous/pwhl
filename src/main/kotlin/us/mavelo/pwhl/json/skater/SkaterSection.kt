package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.skater.headers.Headers

@Serializable
data class SkaterSection (

		var title   : String?         = null,
		var headers : Headers?        = Headers(),
		var data    : ArrayList<Data> = arrayListOf()

)
