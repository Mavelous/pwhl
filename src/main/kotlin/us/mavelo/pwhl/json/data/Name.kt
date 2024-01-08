package us.mavelo.pwhl.json.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Name (

  var playerLink : String? = null,
  var seoName    : String? = null

)
