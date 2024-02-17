package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class TeamTest {
	@Test fun allTeamsExist() {
		assertEquals(6, Team.entries.size)
	}

	@Test fun boston() {
		assertEquals(1, Team.BOSTON.teamNum)
		assertEquals("#164E36", Team.BOSTON.topColor)
		assertEquals("#C3C6CC", Team.BOSTON.bottomColor)
		assertEquals("boston", Team.BOSTON.toString().lowercase())
	}

	@Test fun minnesota() {
		assertEquals(2, Team.MINNESOTA.teamNum)
		assertEquals("#5A288D", Team.MINNESOTA.topColor)
		assertEquals("#000", Team.MINNESOTA.bottomColor)
		assertEquals("minnesota", Team.MINNESOTA.toString().lowercase())
	}

	@Test fun montreal() {
		assertEquals(3, Team.MONTREAL.teamNum)
		assertEquals("#651517", Team.MONTREAL.topColor)
		assertEquals("#000", Team.MONTREAL.bottomColor)
		assertEquals("montreal", Team.MONTREAL.toString().lowercase())
	}

	@Test fun newYork() {
		assertEquals(4, Team.NEW_YORK.teamNum)
		assertEquals("#18B196", Team.NEW_YORK.topColor)
		assertEquals("#151E38", Team.NEW_YORK.bottomColor)
		assertEquals("new_york", Team.NEW_YORK.toString().lowercase())
	}

	@Test fun ottawa() {
		assertEquals(5, Team.OTTAWA.teamNum)
		assertEquals("#BC1F1E", Team.OTTAWA.topColor)
		assertEquals("#383334", Team.OTTAWA.bottomColor)
		assertEquals("ottawa", Team.OTTAWA.toString().lowercase())
	}

	@Test fun toronto() {
		assertEquals(6, Team.TORONTO.teamNum)
		assertEquals("#1A75BD", Team.TORONTO.topColor)
		assertEquals("#000", Team.TORONTO.bottomColor)
		assertEquals("toronto", Team.TORONTO.toString().lowercase())
	}
}
