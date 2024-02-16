package us.mavelo.pwhl.json.common

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class PropTest {
	@Test
	fun canConstruct() {
		val jsonContent: String = this::class.java.getResource("/json/common/prop.json")?.readText(Charsets.UTF_8) ?: ""

		val prop: Prop = Json.decodeFromString<Prop>(jsonContent)

		assertNotNull(prop)
	}
}
