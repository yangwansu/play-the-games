package org.slipp.masil.games.domains.ranking;

import java.util.Optional;

public interface RankingRepository {
    Ranking findById(RankingId id);

    Ranking save(Ranking ranking);
}
