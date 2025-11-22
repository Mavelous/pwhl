package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class TeamTest {
	@Test
	fun allTeamsExist() {
		assertEquals(8, Team.entries.size)
	}

	@Test
	fun boston() {
		assertEquals(1, Team.BOSTON.teamNum)
		assertEquals("BOS", Team.BOSTON.shortName)
		assertEquals("#164E36", Team.BOSTON.topColor)
		assertEquals("#C3C6CC", Team.BOSTON.bottomColor)
		assertEquals("boston", Team.BOSTON.toString().lowercase())
		assertEquals(Team.BOSTON, Team.fromShortName("BOS"))
	}

	@Test
	fun minnesota() {
		assertEquals(2, Team.MINNESOTA.teamNum)
		assertEquals("MIN", Team.MINNESOTA.shortName)
		assertEquals("#250E62", Team.MINNESOTA.topColor)
		assertEquals("#A77BCA", Team.MINNESOTA.bottomColor)
		assertEquals("minnesota", Team.MINNESOTA.toString().lowercase())
		assertEquals(Team.MINNESOTA, Team.fromShortName("MIN"))
	}

	@Test
	fun montreal() {
		assertEquals(3, Team.MONTREAL.teamNum)
		assertEquals("MTL", Team.MONTREAL.shortName)
		assertEquals("#651517", Team.MONTREAL.topColor)
		assertEquals("#000", Team.MONTREAL.bottomColor)
		assertEquals("montreal", Team.MONTREAL.toString().lowercase())
		assertEquals(Team.MONTREAL, Team.fromShortName("MTL"))
	}

	@Test
	fun newYork() {
		assertEquals(4, Team.NEW_YORK.teamNum)
		assertEquals("NYC", Team.NEW_YORK.shortName)
		assertEquals("#18B196", Team.NEW_YORK.topColor)
		assertEquals("#151E38", Team.NEW_YORK.bottomColor)
		assertEquals("new_york", Team.NEW_YORK.toString().lowercase())
		assertEquals(Team.NEW_YORK, Team.fromShortName("NYC"))
	}

	@Test
	fun ottawa() {
		assertEquals(5, Team.OTTAWA.teamNum)
		assertEquals("OTT", Team.OTTAWA.shortName)
		assertEquals("#BC1F1E", Team.OTTAWA.topColor)
		assertEquals("#383334", Team.OTTAWA.bottomColor)
		assertEquals("ottawa", Team.OTTAWA.toString().lowercase())
		assertEquals(Team.OTTAWA, Team.fromShortName("OTT"))
	}

	@Test
	fun toronto() {
		assertEquals(6, Team.TORONTO.teamNum)
		assertEquals("TOR", Team.TORONTO.shortName)
		assertEquals("#1A75BD", Team.TORONTO.topColor)
		assertEquals("#000", Team.TORONTO.bottomColor)
		assertEquals("toronto", Team.TORONTO.toString().lowercase())
		assertEquals(Team.TORONTO, Team.fromShortName("TOR"))
	}

	@Test
	fun vancouver() {
		assertEquals(9, Team.VANCOUVER.teamNum)
		assertEquals("VAN", Team.VANCOUVER.shortName)
		assertEquals("#0F4777", Team.VANCOUVER.topColor)
		assertEquals("#EEE9D8", Team.VANCOUVER.bottomColor)
		assertEquals("vancouver", Team.VANCOUVER.toString().lowercase())
		assertEquals(Team.VANCOUVER, Team.fromShortName("VAN"))
	}

	@Test
	fun seattle() {
		assertEquals(8, Team.SEATTLE.teamNum)
		assertEquals("SEA", Team.SEATTLE.shortName)
		assertEquals("#0A5258", Team.SEATTLE.topColor)
		assertEquals("#EEE9D8", Team.SEATTLE.bottomColor)
		assertEquals("seattle", Team.SEATTLE.toString().lowercase())
		assertEquals(Team.SEATTLE, Team.fromShortName("SEA"))
	}
}
