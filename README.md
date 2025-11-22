# PWHL Data Scraper

This project is currently used to read data from the [PWHL's official stats website](lscluster.hockeytech.com),
parse it, and output text in Wikipedia table format.  I use this to update the team season pages
(e.g., [2025–26 PWHL Minnesota season](https://en.wikipedia.org/wiki/2025–26_Minnesota_Frost_season)).

## Usage

This software is developed on a Mac.

Issuing the `./gradlew run` command will run the program, which will output both Skater and Goalie
stats for each team in the league.

If you want to run it for a specific team, you can pass the team "short code" as a command line argument.
For example, to get data for the Minnesota Frost only, you would run:

```bash
./gradlew run --args="MIN"
```

You can also request multiple teams at the same time.  Example:

```bash
./gradlew run --args="MIN TOR"
```

### Thanks

I would also like to publicly acknowledge my friend, [GeePaw Hill](https://www.geepawhill.org/),
for his help in working through some issues with building this software.  For years, he has pushed
me to continue my learning of Kotlin.  More specifically to this project, he has been a knowledgeable
sounding board when I have run into issues with the _very_ inconsistent data returned by the
PWHL official stats website.
