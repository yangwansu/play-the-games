CREATE TABLE IF NOT EXISTS RANKING(id bigint primary key, size integer, version bigint)
CREATE TABLE IF NOT EXISTS RANKING_ITEM(ranking_id bigint, ranking_key integer, user_name varchar(100), score integer, ranked_at timestamp)
CREATE TABLE IF NOT EXISTS HIGH_LOW_PLAYING_CONTEXT(id bigint primary key identity, game_id bigint, user_name varchar(100), start_at timestamp, target integer, state varchar(100), score integer, version bigint, UNIQUE(game_id, user_name))
