package org.slipp.masil.games.domains.highrow;

import static org.assertj.core.api.Assertions.*;
import static org.slipp.masil.games.domains.PlayState.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.HighLowResultOfTurn;
import org.slipp.masil.games.domains.HighLowTurn;
import org.slipp.masil.games.domains.PlayState;
import org.slipp.masil.games.domains.Score;

class HighLowPlayTest {


    String userName = "Mike";
    int target = 10;
    HighLowPlay sut;

    @BeforeEach
    void setUp() {
        sut = HighLowPlay.by(target);
        assertThat(sut.getTarget()).isEqualTo(target);
        assertThat(sut.getState()).isEqualTo(ON_GAME);
        assertThat(sut.getScore()).isEqualTo(Score.of(0));
        assertThat(sut.getLastResultOfTurn()).isEqualTo(HighLowResultOfTurn.NONE);
    }

    @Test
    void guessByTurn() {
        HighLowPlay guess1 = sut.by(HighLowTurn.of(1));
        assertThat(guess1).isNotSameAs(sut);
        assertThat(guess1.getState()).isEqualTo(ON_GAME);
        assertThat(guess1.getLastResultOfTurn()).isEqualTo(HighLowResultOfTurn.isLow());

        HighLowPlay guess11 = guess1.by(HighLowTurn.of(11));
        assertThat(guess11.getState()).isEqualTo(ON_GAME);
        assertThat(guess11.getLastResultOfTurn()).isEqualTo(HighLowResultOfTurn.isHigh());

        HighLowPlay guess10 = guess11.by(HighLowTurn.of(10));
        assertThat(guess10.getState()).isEqualTo(ENDED);
        assertThat(guess10.getLastResultOfTurn()).isEqualTo(HighLowResultOfTurn.isMatched());

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