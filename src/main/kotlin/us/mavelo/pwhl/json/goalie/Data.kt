package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.common.Prop

@Serializable
data class Data (

		@SerialName("prop" ) var prop : Prop? = Prop(),
		@SerialName("row"  ) var row  : GoalieRow?  = GoalieRow()

)
