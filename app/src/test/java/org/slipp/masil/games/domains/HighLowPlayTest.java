package org.slipp.masil.games.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.PlayState.ENDED;
import static org.slipp.masil.games.domains.PlayState.ON_GAME;

class HighLowPlayTest {


    String userName = "Mike";
    int target = 10;
    HighLowPlay sut;

    @BeforeEach
    void setUp() {

        sut = HighLowPlay.by(userName, target);
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
}