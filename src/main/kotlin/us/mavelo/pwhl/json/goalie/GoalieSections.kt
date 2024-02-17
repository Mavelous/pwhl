package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.Serializable

@Serializable
data class GoalieSections(var sections: ArrayList<GoalieSection> = arrayListOf())
