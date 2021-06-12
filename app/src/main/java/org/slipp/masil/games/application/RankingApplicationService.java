package org.slipp.masil.games.application;

import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;
import org.slipp.masil.games.domains.ranking.Ranking;
import org.slipp.masil.games.domains.ranking.RankingId;
import org.slipp.masil.games.domains.ranking.RankingItem;
import org.slipp.masil.games.domains.ranking.RankingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RankingApplicationService {

    private final RankingRepository repository;

    public RankingApplicationService(RankingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void refresh(GameId gameId,
                        String userName,
                        Score score) {
        Ranking ranking = repository.findById(RankingId.of(gameId));

        RankingItem newInfo = RankingItem.of(userName, score, LocalDateTime.now());
        ranking.refresh(newInfo);
        repository.save(ranking);
    }
}
