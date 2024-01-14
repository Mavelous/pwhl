# PWHL Data Scraper

This project is currently used to read data from the [PWHL's official stats website](lscluster.hockeytech.com),
parse it, and output text in Wikipedia table format.  I use this to update the 2024 team season pages
(e.g., [2024 PWHL Minnesota season](https://en.wikipedia.org/wiki/2024_PWHL_Minnesota_season)).

## Usage

This software is developed on a Mac.

Issuing the `./gradlew run` command will run the program, which will output both Skater and Goalie
stats for each team in the league.

### Thanks

I would also like to publicly acknowledge my friend, [GeePaw Hill](https://www.geepawhill.org/),
for his help in working through some issues with building this software.  For years, he has pushed
me to continue my learning of Kotlin.  More specifically to this project, he has been a knowledgeable
sounding board when I have run into issues with the _very_ inconsistent data returned by the
PWHL official stats website.
