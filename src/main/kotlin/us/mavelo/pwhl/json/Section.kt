package us.mavelo.pwhl.json

import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.player.Data
import us.mavelo.pwhl.json.headers.Headers

@Serializable
data class Section (

		var title   : String?         = null,
		var headers : Headers?        = Headers(),
		var data    : ArrayList<Data> = arrayListOf()

)
