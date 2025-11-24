package us.mavelo.pwhl

enum class Season(val id: Int, val title: String, val type: String) {
	SEASON_2024_REGULAR_SEASON(1, "2024 Regular Season", "Regular Season"),
	SEASON_2024_PRESEASON(2, "2024 Preseason", "Preseason"),
	SEASON_2024_PLAYOFFS(3, "2024 Playoffs", "Playoffs"),
	SEASON_2024_25_PRESEASON(4, "2024–25 Preseason", "Preseason"),
	SEASON_2024_25_REGULAR_SEASON(5, "2024–25 Regular Season", "Regular Season"),
	SEASON_2025_PLAYOFFS(6, "2025 Playoffs", "Playoffs"),
	SEASON_2025_26_PRESEASON(7, "2025–26 Preseason", "Preseason"),
	SEASON_2025_26_REGULAR_SEASON(8, "2025–26 Regular Season", "Regular Season")
}
