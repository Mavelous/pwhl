package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class SkaterUrlTest {
	val currentSeason: Season = Season.SEASON_2025_26_REGULAR_SEASON
	val skaterUrl = SkaterUrl(currentSeason)

	@Test
	fun testGetPosition() {
		assertEquals("skaters", skaterUrl.getPosition())
	}

	@Test
	fun testGetSort() {
		assertEquals("points", skaterUrl.getSort())
	}

	@Test
	fun testGetStatsUrl() {
		val expectedUrl = "https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=8&team=2&position=skaters&statsType=standard&sort=points&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl"
		assertEquals(expectedUrl, skaterUrl.getStatsUrl(2))
	}
}
