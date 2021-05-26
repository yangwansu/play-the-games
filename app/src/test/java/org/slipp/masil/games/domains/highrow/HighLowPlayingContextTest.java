package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.HighLowResultOfTurn;
import org.slipp.masil.games.domains.HighLowTurn;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.slipp.masil.games.domains.PlayState.ENDED;
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
        assertThat(sut.getHighLowResultOfTurn()).isEqualTo(HighLowResultOfTurn.NONE);
    }

    @Test
    void guessByTurn() {
        HighLowPlayingContext guess1 = sut.by(HighLowTurn.of(1));
        assertThat(guess1).isNotSameAs(sut);
        assertThat(guess1.getState()).isEqualTo(ON_GAME);
        assertThat(guess1.getHighLowResultOfTurn()).isEqualTo(HighLowResultOfTurn.isLow());

        HighLowPlayingContext guess11 = guess1.by(HighLowTurn.of(11));
        assertThat(guess11.getState()).isEqualTo(ON_GAME);
        assertThat(guess11.getHighLowResultOfTurn()).isEqualTo(HighLowResultOfTurn.isHigh());

        HighLowPlayingContext guess10 = guess11.by(HighLowTurn.of(10));
        assertThat(guess10.getState()).isEqualTo(ENDED);
        assertThat(guess10.getHighLowResultOfTurn()).isEqualTo(HighLowResultOfTurn.isMatched());

    }

    @Test
    void Play_가_종료되면_상태가_변경된다() {
        assertThat(sut.getState()).isEqualTo(ON_GAME);

        sut.exit();

        assertThat(sut.getState()).isEqualTo(ENDED);
    }

    @Test
    void 종료된_Play에서_다시_종료시킬경우_IllegalStateException_호출한다() {
        assertThat(sut.getState()).isEqualTo(ON_GAME);
        sut.exit();
        assertThat(sut.getState()).isEqualTo(ENDED);

        assertThatThrownBy(() -> sut.exit())
            .isInstanceOf(IllegalStateException.class).hasMessageContaining("play has already ended");
    }

}
