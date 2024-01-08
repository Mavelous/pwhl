package us.mavelo

data class ScrapingResult(val countries: MutableList<Country> = mutableListOf(), var count:Int = 0)
