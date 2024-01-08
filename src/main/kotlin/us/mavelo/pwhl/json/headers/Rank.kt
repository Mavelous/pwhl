package us.mavelo.pwhl.json.headers

import kotlinx.serialization.Serializable

@Serializable
data class Rank (
  var properties : Properties? = Properties()
)
