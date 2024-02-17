package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.common.Prop

@Serializable
data class Data(
		var prop: Prop? = Prop(),
		var row: SkaterRow? = SkaterRow(),
		var teams: ArrayList<Team>? = arrayListOf()
)
