package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable

@Serializable
data class SkaterSections(var sections: ArrayList<SkaterSection> = arrayListOf())
