package org.slipp.masil.games.domains.ranking;

import lombok.Value;
import org.slipp.masil.games.domains.game.GameId;

@Value(staticConstructor = "of")
public class RankingId {
    GameId gameId;

    public static RankingId of(Long id) {
        return of(GameId.of(id));
    }
}
