package us.mavelo.pwhl.json.goalie.headers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import us.mavelo.pwhl.json.goalie.Properties

@Serializable
data class Active (

  @SerialName("properties" ) var properties : Properties? = Properties()

)
