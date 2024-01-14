package us.mavelo.pwhl.json.skater

import kotlinx.serialization.Serializable

@Serializable
data class Name (

  var playerLink : String? = null,
  var seoName    : String? = null

)
