package us.mavelo

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.*

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    val websiteUrl = "https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population"
    val countries = skrape(HttpFetcher) {
        request {
            url = websiteUrl
        }

        extractIt<ScrapingResult> { results ->
            htmlDocument{
                val countryRows = table(".wikitable") {
                    tr{
                        findAll{this}
                    }
                }

                countryRows
                    .drop(2)  // Remove the first two elements; these are just the table header and subheader
                    .map{
                        // Define variables to hold name and population
                        var name: String =""
                        var population: String=""
                        it.a{
                            findFirst(){   // Find the first <a> tag
                                name = text    // Extract its text (this is the name of the country)
                                println("Name - $text ")
                            }
                        }
                        it.td{
                            findSecond(){    // Find the second <td> tag
                                population = text   // Extract its text (this is the population of the country)
                                println("Population - $text \n")
                            }
                        }
                        results.countries.add(Country(name,population))   // Create a country and add it to the results object
                        results.count = results.countries.size  // Get the number of countries and add it to the results object
                    }
            }
        }
    }
}
