# PWHL Player Statistics Database Schema

## Overview
A comprehensive database schema for tracking PWHL (Professional Women's Hockey League) player statistics over time, supporting both skaters and goalies.

## Tables

### Reference Tables

#### `teams` (8 rows)
- **Purpose**: PWHL team reference data
- **Key Columns**: `id`, `team_number`, `short_name`, `full_name`, `top_color`, `bottom_color`
- **Teams**: Boston Fleet (BOS), Minnesota Frost (MIN), Montreal Victoire (MTL), New York Sirens (NYC), Ottawa Charge (OTT), Toronto Sceptres (TOR), Vancouver Goldeneyes (VAN), Seattle Torrent (SEA)

#### `seasons` (8 rows)
- **Purpose**: Season reference data
- **Key Columns**: `id`, `season_id`, `title`, `season_type`
- **Coverage**: 2024 Preseason through 2025-26 Regular Season (includes preseason, regular season, and playoffs)

#### `players`
- **Purpose**: Core player information
- **Key Columns**: `id`, `player_id` (unique), `name`, `position`, `active`, `rookie`
- **Timestamp Columns**: `created_at`, `updated_at`
- **Use**: Central registry of all players in the system

### Statistics Tables

#### `skater_statistics` (32 columns)
- **Purpose**: Cumulative skater statistics per season
- **Key Relationships**: References `players`, `teams`, `seasons`
- **Unique Constraint**: One record per player/team/season/recorded_at combination
- **Tracked Statistics**:
  - **Basic**: Games played, goals, assists, points
  - **Shooting**: Shots, shooting percentage
  - **Penalties**: Penalty minutes, penalty minutes per game
  - **Physical**: Hits, hits per game average, shots blocked
  - **Ice Time**: Minutes/seconds, per-game average
  - **Plus/Minus**: Overall plus/minus rating
  - **Power Play**: PP goals, PP assists
  - **Short Handed**: SH goals, SH assists
  - **Shootout**: SO goals, SO attempts, SO winning goals, SO percentage
  - **Faceoffs**: Attempts, wins, win percentage
  - **Metadata**: Rank, recorded_at, updated_at

#### `goalie_statistics` (25 columns)
- **Purpose**: Cumulative goalie statistics per season
- **Key Relationships**: References `players`, `teams`, `seasons`
- **Unique Constraint**: One record per player/team/season/recorded_at combination
- **Tracked Statistics**:
  - **Basic**: Games played, minutes played
  - **Game Results**: Wins, losses, OT losses, shootout losses
  - **Saves**: Saves, shots against, save percentage
  - **Goals Against**: Goals against, goals against average, shutouts
  - **Shootout**: SO goals against, SO attempts, SO percentage
  - **Player Stats**: Goals, assists, penalty minutes (rare for goalies)
  - **Metadata**: Rank, recorded_at, updated_at

## Views

### `current_skater_season_stats`
- **Purpose**: Quickly access the latest skater statistics for the current season
- **Columns**: player_id, name, position, team, season, games_played, goals, assists, points, plus_minus, penalty_minutes, shooting_percentage, recorded_at
- **Query**: Automatically returns the most recently recorded stats for each player per season

### `current_goalie_season_stats`
- **Purpose**: Quickly access the latest goalie statistics for the current season
- **Columns**: player_id, name, team, season, games_played, wins, losses, ot_losses, save_percentage, goals_against_average, shutouts, recorded_at
- **Query**: Automatically returns the most recently recorded stats for each player per season

## Indexes

For query performance, indexes are created on:
- `skater_statistics(player_id, season_id)`
- `skater_statistics(team_id, season_id)`
- `skater_statistics(recorded_at)`
- `goalie_statistics(player_id, season_id)`
- `goalie_statistics(team_id, season_id)`
- `goalie_statistics(recorded_at)`
- `players(name)`
- `players(player_id)`

## Data Constraints

### Percentage Validation
- Shooting percentage: 0-100%
- Faceoff percentage: 0-100%
- Save percentage: 0-100%
- Shootout percentage: 0-100%

### Goalie-Specific Constraints
- Goals against average >= 0
- Save percentage: 0-100%
- Shootout percentage: 0-100%

## Time-Based Tracking

The `recorded_at` timestamp allows you to track:
- **Game-by-game statistics**: Record a new row after each game
- **Cumulative season stats**: Update or insert rows as stats accumulate
- **Historical trending**: Query stats across multiple timestamps to analyze progression
- **Multiple snapshots**: Store different versions of stats at different points in a season

### Example Queries

```sql
-- Get current skater stats for a specific player
SELECT * FROM current_skater_season_stats WHERE name = 'Megan Keller';

-- Get team's total stats for current season
SELECT team, SUM(goals) as total_goals, SUM(assists) as total_assists
FROM current_skater_season_stats
WHERE team = 'BOS'
GROUP BY team;

-- Compare player stats over time
SELECT recorded_at, goals, assists, points
FROM skater_statistics
WHERE player_id = (SELECT id FROM players WHERE name = 'Megan Keller')
  AND season_id = (SELECT id FROM seasons WHERE title = '2025–26 Regular Season')
ORDER BY recorded_at;

-- Goalie performance by team
SELECT name, team, wins, losses, save_percentage
FROM current_goalie_season_stats
WHERE team = 'BOS'
ORDER BY save_percentage DESC;
```

## Migration History

- **V1**: Initial flyway_test table (for verification)
- **V2**: Complete player statistics schema with teams, seasons, players, skater_statistics, goalie_statistics, views, and reference data

## Future Enhancements

Potential additions to the schema:
- Game results table for individual game-level data
- Player transactions (trades, signings, releases)
- Injury tracking
- Team standings table
- Historical comparisons/analytics tables
- Advanced metrics (expected goals, etc.)

