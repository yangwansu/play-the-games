package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.PlayState.*;

class HighLowPlayingContextTest {

    GameId gameId = GameId.of(1L);
    String userName = "Mike";
    HighLowPlayingContext sut;

    @BeforeEach
    void setUp() {
        sut = HighLowPlayingContext.by(gameId, userName);
    }

    @Test
    void init() {
        assertThat(sut.getGameId()).isEqualTo(gameId);
        assertThat(sut.getUserName()).isEqualTo(userName);
        assertThat(sut.getStartAt()).isEqualToIgnoringNanos(LocalDateTime.now());
        assertThat(sut.getState()).isEqualTo(INIT);
        assertThat(sut.getScore()).isEqualTo(Score.of(0));
    }

    @Test
    void start() {
        sut.start();

        assertThat(sut.isOn()).isTrue();
    }

    @Test
    void stop() {
        sut.start();
        sut.stop();

        assertThat(sut.isOff()).isTrue();
    }

    @Test
    void match() {
        sut.start();
        sut.match();

        assertThat(sut.isOff()).isTrue();
    }

    @Test
    void tryPlay() {
        sut.start();
        sut.tryPlay();

        assertThat(sut.getScore()).isEqualTo(Score.of(1));
    }
}
