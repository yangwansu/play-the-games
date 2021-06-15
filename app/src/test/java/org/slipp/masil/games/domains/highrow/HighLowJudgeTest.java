package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.Target;
import org.slipp.masil.games.domains.game.DifficultyLevel;

import static org.assertj.core.api.Assertions.assertThat;

// TODO Target 을 Context 가 알아야 하는 이유는 무엇일까?
//  그럼 Score 와 같은 동급이라는 건데, 그런 의미가 있나?
class HighLowJudgeTest {

    HighLowJudge sut;
    Target target;
    DifficultyLevel dl = DifficultyLevel.EASY;

    @BeforeEach
    void setUp() {
        sut = new HighLowJudge(dl);
        target = sut.getTarget();
    }

    @Test
    void createTarget() {
        assertThat(target.getValue()).isBetween(0L, 10L);
    }

    @Test
    void match() {
        Long guessNumber = 1L;
        HighLowJudgement result = HighLowJudgement.MATCH;

        assertThat(sut.judge(guessNumber)).isEqualTo(result);
    }

    @Test
    void wrongGuess() {
        Long guessNumber = 9L;
        HighLowJudgement result = HighLowJudgement.HIGH;

        assertThat(sut.judge(guessNumber)).isEqualTo(result);
    }

    @Test
    void replay() {
        long guessNumber = 1L;
        HighLowJudgement result = HighLowJudgement.MATCH;
        assertThat(sut.judge(guessNumber)).isEqualTo(result);
        guessNumber = 9L;

        sut.reset();

        assertThat(sut.judge(guessNumber)).isEqualTo(HighLowJudgement.HIGH);
    }
}
