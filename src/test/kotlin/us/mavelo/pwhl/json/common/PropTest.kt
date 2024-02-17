package us.mavelo.pwhl.json.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PropTest {
	@Test
	fun isSerializable() {
		assertEquals(1, Prop::class.annotations.size)
		assertNotNull(Prop::class.annotations.find { it is Serializable })
	}

	@Test
	fun canConstructProp() {
		val prop: Prop = Json.decodeFromString("""{
	"name": {
		"playerLink": "100",
		"seoName": "Natalie Spooner"
	},
	"active": {
		"active": "1"
	},
	"rookie": {
		"rookie": "0"
	},
	"team_code": {
		"teamLink": "6"
	}
}
""")

		assertEquals("Natalie Spooner", prop.name!!.seoName)
		assertEquals("1", prop.active!!.active)
		assertEquals("0", prop.rookie!!.rookie)
		assertEquals("6", prop.teamCode!!.teamLink)
	}

	@Test
	fun canConstructPropFromFile() {
		val jsonContent: String = this::class.java.getResource("/json/common/prop.json")?.readText(Charsets.UTF_8) ?: ""

		val prop: Prop = Json.decodeFromString<Prop>(jsonContent)

		assertEquals("Natalie Spooner", prop.name!!.seoName)
		assertEquals("1", prop.active!!.active)
		assertEquals("0", prop.rookie!!.rookie)
		assertEquals("6", prop.teamCode!!.teamLink)
	}
}
