package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class SkaterUrlTest {
	@Test
	fun testGetPosition() {
		val skaterUrl = SkaterUrl()
		assertEquals("skaters", skaterUrl.getPosition())
	}

	@Test
	fun testGetSort() {
		val skaterUrl = SkaterUrl()
		assertEquals("points", skaterUrl.getSort())
	}

	@Test
	fun testGetStatsUrl() {
		val skaterUrl = SkaterUrl()
		val expectedUrl = "https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=8&team=2&position=skaters&statsType=standard&sort=points&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl"
		assertEquals(expectedUrl, skaterUrl.getStatsUrl(2))
	}
}
