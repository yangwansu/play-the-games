package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.PlayState.ON_GAME;

class HighLowPlayingContextTest {

    String userName = "Mike";
    int target = 10;
    HighLowPlayingContext sut;

    @BeforeEach
    void setUp() {
        GameId gameId = GameId.of(1L);
        LocalDateTime startAt = LocalDateTime.now();
        sut = HighLowPlayingContext.by(gameId, userName, startAt, target);

        assertThat(sut.getGameId()).isEqualTo(gameId);
        assertThat(sut.getUserName()).isEqualTo(userName);
        assertThat(sut.getStartAt()).isEqualTo(startAt);
        assertThat(sut.getTarget()).isEqualTo(target);
        assertThat(sut.getState()).isEqualTo(ON_GAME);
        assertThat(sut.getScore()).isEqualTo(Score.of(0));
    }
}
