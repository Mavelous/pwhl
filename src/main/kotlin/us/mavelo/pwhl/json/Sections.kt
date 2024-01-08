package us.mavelo.pwhl.json

import kotlinx.serialization.Serializable

@Serializable
data class Sections(var sections: ArrayList<Section> = arrayListOf())
