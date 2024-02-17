package us.mavelo.pwhl.json.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.skater.Active
import us.mavelo.pwhl.json.skater.Name
import us.mavelo.pwhl.json.skater.Rookie
import us.mavelo.pwhl.json.skater.TeamCode

@Serializable
data class Prop(
		var name: Name? = Name(),
		var active: Active? = Active(),
		var rookie: Rookie? = Rookie(),
		@SerialName("team_code") var teamCode: TeamCode? = TeamCode()
)
