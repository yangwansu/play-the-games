package org.slipp.masil.games.domains.ranking;


public interface RankingRepository {
    Ranking findById(RankingId id);

    Ranking save(Ranking ranking);
}
