package us.mavelo.pwhl.json.player

import kotlinx.serialization.Serializable

@Serializable
data class Data (

		var prop : Prop? = Prop(),
		var row  : SkaterRow?  = SkaterRow()

)
