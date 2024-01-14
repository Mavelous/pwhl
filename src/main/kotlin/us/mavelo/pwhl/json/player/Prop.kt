package us.mavelo.pwhl.json.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prop (

		var name     : Name?     = Name(),
		var active   : Active?   = Active(),
		var rookie   : Rookie?   = Rookie(),
		@SerialName("team_code") var teamCode : TeamCode? = TeamCode()

)
