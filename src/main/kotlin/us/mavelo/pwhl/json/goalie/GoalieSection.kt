package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoalieSection (

		@SerialName("title"   ) var title   : String?         = null,
		@SerialName("headers" ) var headers : Headers?        = Headers(),
		@SerialName("data"    ) var data    : ArrayList<Data> = arrayListOf()

)
