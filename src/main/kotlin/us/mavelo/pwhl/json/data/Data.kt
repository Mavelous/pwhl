package us.mavelo.pwhl.json.data

import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.data.Prop
import us.mavelo.pwhl.json.data.Row

@Serializable
data class Data (

		var prop : Prop? = Prop(),
		var row  : Row?  = Row()

)
