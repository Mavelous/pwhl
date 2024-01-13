package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable

@Serializable
data class Data (

		var prop : Prop? = Prop(),
		var row  : Row?  = Row()

)
