package us.mavelo.pwhl.json.goalie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Properties(

		@SerialName("key") var key: String? = null,
		@SerialName("hidden") var hidden: Boolean? = null,
		@SerialName("class") var _class: String? = null,
		@SerialName("label") var label: String? = null,
		@SerialName("title") var title: String? = null,
		@SerialName("sortable") var sortable: Boolean? = null,
		@SerialName("align") var align: String? = null,
		@SerialName("highlight") var highlight: Boolean? = null,
		@SerialName("sortKey") var sortKey: String? = null

)
