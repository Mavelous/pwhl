-- Database schema for tracking PWHL player statistics over time
-- Supports both skaters and goalies with comprehensive game-by-game tracking

-- Teams reference table
CREATE TABLE teams (
    id SERIAL PRIMARY KEY,
    team_number INT NOT NULL UNIQUE,
    short_name VARCHAR(10) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    top_color VARCHAR(7),
    bottom_color VARCHAR(7),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seasons reference table
CREATE TABLE seasons (
    id SERIAL PRIMARY KEY,
    season_id INT NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    season_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Players base table
CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    player_id VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(10),
    active BOOLEAN DEFAULT TRUE,
    rookie BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Skater statistics (cumulative stats per season/period)
CREATE TABLE skater_statistics (
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL REFERENCES players(id),
    team_id INT NOT NULL REFERENCES teams(id),
    season_id INT NOT NULL REFERENCES seasons(id),

    -- Basic stats
    games_played INT DEFAULT 0,
    goals INT DEFAULT 0,
    assists INT DEFAULT 0,
    points INT DEFAULT 0,

    -- Shooting stats
    shots INT DEFAULT 0,
    shooting_percentage DECIMAL(5, 2),

    -- Penalty and physical stats
    penalty_minutes INT DEFAULT 0,
    penalty_minutes_per_game DECIMAL(5, 2),
    hits INT DEFAULT 0,
    hits_per_game_avg DECIMAL(5, 2),
    shots_blocked_by_player INT DEFAULT 0,

    -- Ice time stats
    ice_time_minutes_seconds VARCHAR(20),
    ice_time_per_game_avg VARCHAR(20),

    -- Plus/minus
    plus_minus INT DEFAULT 0,

    -- Power play stats
    power_play_goals INT DEFAULT 0,
    power_play_assists INT DEFAULT 0,

    -- Short handed stats
    short_handed_goals INT DEFAULT 0,
    short_handed_assists INT DEFAULT 0,

    -- Shootout stats
    shootout_goals INT DEFAULT 0,
    shootout_attempts INT DEFAULT 0,
    shootout_winning_goals INT DEFAULT 0,
    shootout_percentage DECIMAL(5, 2),

    -- Faceoff stats
    faceoff_attempts INT DEFAULT 0,
    faceoff_wins INT DEFAULT 0,
    faceoff_pct DECIMAL(5, 2),

    -- Ranking and metadata
    rank INT,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(player_id, team_id, season_id, recorded_at),
    CONSTRAINT valid_shooting_pct CHECK (shooting_percentage >= 0 AND shooting_percentage <= 100),
    CONSTRAINT valid_faceoff_pct CHECK (faceoff_pct >= 0 AND faceoff_pct <= 100)
);

-- Goalie statistics (cumulative stats per season/period)
CREATE TABLE goalie_statistics (
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL REFERENCES players(id),
    team_id INT NOT NULL REFERENCES teams(id),
    season_id INT NOT NULL REFERENCES seasons(id),

    -- Basic stats
    games_played INT DEFAULT 0,
    minutes_played INT DEFAULT 0,

    -- Game results
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    ot_losses INT DEFAULT 0,
    shootout_losses INT DEFAULT 0,

    -- Saves and goals against
    saves INT DEFAULT 0,
    shots INT DEFAULT 0,
    save_percentage DECIMAL(5, 2),
    goals_against INT DEFAULT 0,
    goals_against_average DECIMAL(5, 2),
    shutouts INT DEFAULT 0,

    -- Shootout stats
    shootout_goals_against INT DEFAULT 0,
    shootout_attempts INT DEFAULT 0,
    shootout_percentage DECIMAL(5, 2),

    -- Player stats (rare for goalies but trackable)
    goals INT DEFAULT 0,
    assists INT DEFAULT 0,
    penalty_minutes INT DEFAULT 0,

    -- Ranking and metadata
    rank INT,
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(player_id, team_id, season_id, recorded_at),
    CONSTRAINT valid_save_pct CHECK (save_percentage >= 0 AND save_percentage <= 100),
    CONSTRAINT valid_gaa CHECK (goals_against_average >= 0),
    CONSTRAINT valid_shootout_pct CHECK (shootout_percentage >= 0 AND shootout_percentage <= 100)
);

-- Create indexes for common queries
CREATE INDEX idx_skater_statistics_player_season
    ON skater_statistics(player_id, season_id);
CREATE INDEX idx_skater_statistics_team_season
    ON skater_statistics(team_id, season_id);
CREATE INDEX idx_skater_statistics_recorded_at
    ON skater_statistics(recorded_at);

CREATE INDEX idx_goalie_statistics_player_season
    ON goalie_statistics(player_id, season_id);
CREATE INDEX idx_goalie_statistics_team_season
    ON goalie_statistics(team_id, season_id);
CREATE INDEX idx_goalie_statistics_recorded_at
    ON goalie_statistics(recorded_at);

CREATE INDEX idx_players_name ON players(name);
CREATE INDEX idx_players_player_id ON players(player_id);

-- Views for easy querying

-- Current season skater stats (latest recorded)
CREATE VIEW current_skater_season_stats AS
SELECT
    p.player_id,
    p.name,
    p.position,
    t.short_name as team,
    s.title as season,
    ss.games_played,
    ss.goals,
    ss.assists,
    ss.points,
    ss.plus_minus,
    ss.penalty_minutes,
    ss.shooting_percentage,
    ss.recorded_at
FROM skater_statistics ss
JOIN players p ON ss.player_id = p.id
JOIN teams t ON ss.team_id = t.id
JOIN seasons s ON ss.season_id = s.id
WHERE (p.id, ss.season_id, ss.recorded_at) IN (
    SELECT player_id, season_id, MAX(recorded_at)
    FROM skater_statistics
    GROUP BY player_id, season_id
);

-- Current season goalie stats (latest recorded)
CREATE VIEW current_goalie_season_stats AS
SELECT
    p.player_id,
    p.name,
    t.short_name as team,
    s.title as season,
    gs.games_played,
    gs.wins,
    gs.losses,
    gs.ot_losses,
    gs.save_percentage,
    gs.goals_against_average,
    gs.shutouts,
    gs.recorded_at
FROM goalie_statistics gs
JOIN players p ON gs.player_id = p.id
JOIN teams t ON gs.team_id = t.id
JOIN seasons s ON gs.season_id = s.id
WHERE (p.id, gs.season_id, gs.recorded_at) IN (
    SELECT player_id, season_id, MAX(recorded_at)
    FROM goalie_statistics
    GROUP BY player_id, season_id
);

-- Populate teams reference table
INSERT INTO teams (team_number, short_name, full_name, top_color, bottom_color) VALUES
(1, 'BOS', 'Boston Fleet', '#173F35', '#B5E3D8'),
(2, 'MIN', 'Minnesota Frost', '#250E62', '#A77BCA'),
(3, 'MTL', 'Montreal Victoire', '#862633', '#041E42'),
(4, 'NYC', 'New York Sirens', '#00BFB3', '#041E42'),
(5, 'OTT', 'Ottawa Charge', '#A6192E', '#4B4F54'),
(6, 'TOR', 'Toronto Sceptres', '#0067B9', '#0C2340'),
(9, 'VAN', 'Vancouver Goldeneyes', '#0F4777', '#EEE9D8'),
(8, 'SEA', 'Seattle Torrent', '#0C5256', '#E1DBC9')
ON CONFLICT (short_name) DO NOTHING;

-- Populate seasons reference table
INSERT INTO seasons (season_id, title, season_type) VALUES
(1, '2024 Regular Season', 'Regular Season'),
(2, '2024 Preseason', 'Preseason'),
(3, '2024 Playoffs', 'Playoffs'),
(4, '2024–25 Preseason', 'Preseason'),
(5, '2024–25 Regular Season', 'Regular Season'),
(6, '2025 Playoffs', 'Playoffs'),
(7, '2025–26 Preseason', 'Preseason'),
(8, '2025–26 Regular Season', 'Regular Season')
ON CONFLICT (season_id) DO NOTHING;

-- Drop the test table from V1
DROP TABLE IF EXISTS flyway_test;

