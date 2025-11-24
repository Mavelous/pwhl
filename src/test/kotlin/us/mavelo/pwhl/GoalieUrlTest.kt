package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class GoalieUrlTest {
	val currentSeason: Season = Season.SEASON_2025_26_REGULAR_SEASON
	val goalieUrl = GoalieUrl(currentSeason)

	@Test
	fun testGetPosition() {
		assertEquals("goalies", goalieUrl.getPosition())
	}

	@Test
	fun testGetSort() {
		assertEquals("gp", goalieUrl.getSort())
	}

	@Test
	fun testGetStatsUrl() {
		val expectedUrl = "https://lscluster.hockeytech.com/feed/index.php?feed=statviewfeed&view=players&season=8&team=2&position=goalies&statsType=standard&sort=gp&league_id=1&lang=en&division=-1&key=694cfeed58c932ee&client_code=pwhl"
		assertEquals(expectedUrl, goalieUrl.getStatsUrl(2))
	}
}
