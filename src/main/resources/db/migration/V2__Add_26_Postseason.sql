INSERT INTO seasons (season_id, title, season_type) VALUES
(9, '2026 Playoffs', 'Playoffs')
ON CONFLICT (season_id) DO NOTHING;
