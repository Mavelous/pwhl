package us.mavelo.pwhl.json.skater.headers

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties (

  var key       : String?  = null,
  var hidden    : Boolean? = null,
  @SerialName("class") var _class     : String?  = null,
  var label     : String?  = null,
  var title     : String?  = null,
  var sortable  : Boolean? = null,
  var align     : String?  = null,
  var highlight : Boolean? = null,
  var sortKey   : String?  = null

)
