package us.mavelo.pwhl

import kotlin.test.Test
import kotlin.test.assertEquals

class SeasonTest {
	@Test
	fun correctNumberOfSeason() {
		val seasons = Season.entries
		assertEquals(8, seasons.size)
	}

	@Test
	fun firstSeason() {
		val season = Season.values()[0]
		assertEquals(Season.SEASON_2024_REGULAR_SEASON, season)
		assertEquals(1, season.id)
		assertEquals("2024 Regular Season", season.title)
		assertEquals("Regular Season", season.type)
	}

	@Test
	fun secondSeason() {
		val season = Season.values()[1]
		assertEquals(Season.SEASON_2024_PRESEASON, season)
		assertEquals(2, season.id)
		assertEquals("2024 Preseason", season.title)
		assertEquals("Preseason", season.type)
	}

	@Test
	fun thirdSeason() {
		val season = Season.values()[2]
		assertEquals(Season.SEASON_2024_PLAYOFFS, season)
		assertEquals(3, season.id)
		assertEquals("2024 Playoffs", season.title)
		assertEquals("Playoffs", season.type)
	}

	@Test
	fun fourthSeason() {
		val season = Season.values()[3]
		assertEquals(Season.SEASON_2024_25_PRESEASON, season)
		assertEquals(4, season.id)
		assertEquals("2024–25 Preseason", season.title)
		assertEquals("Preseason", season.type)
	}

	@Test
	fun fifthSeason() {
		val season = Season.values()[4]
		assertEquals(Season.SEASON_2024_25_REGULAR_SEASON, season)
		assertEquals(5, season.id)
		assertEquals("2024–25 Regular Season", season.title)
		assertEquals("Regular Season", season.type)
	}

	@Test
	fun sixthSeason() {
		val season = Season.values()[5]
		assertEquals(Season.SEASON_2025_PLAYOFFS, season)
		assertEquals(6, season.id)
		assertEquals("2025 Playoffs", season.title)
		assertEquals("Playoffs", season.type)
	}

	@Test
	fun seventhSeason() {
		val season = Season.values()[6]
		assertEquals(Season.SEASON_2025_26_PRESEASON, season)
		assertEquals(7, season.id)
		assertEquals("2025–26 Preseason", season.title)
		assertEquals("Preseason", season.type)
	}

	@Test
	fun eighthSeason() {
		val season = Season.values()[7]
		assertEquals(Season.SEASON_2025_26_REGULAR_SEASON, season)
		assertEquals(8, season.id)
		assertEquals("2025–26 Regular Season", season.title)
		assertEquals("Regular Season", season.type)
	}
}
