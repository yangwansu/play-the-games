package org.slipp.masil.games.domains.highrow;

import org.slipp.masil.games.domains.Judge;
import org.slipp.masil.games.domains.Target;
import org.slipp.masil.games.domains.game.DifficultyLevel;


public class HighLowJudge implements Judge {

    private final DifficultyLevel difficultyLevel;
    private Target target;

    public HighLowJudge(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        this.target = difficultyLevel.create();
    }

    @Override
    public HighLowJudgement judge(Long guessNumber) {
        Long value = this.target.getValue();
        if (value > guessNumber) {
            return HighLowJudgement.LOW;
        } else if (value < guessNumber) {
            return HighLowJudgement.HIGH;
        }
        return HighLowJudgement.MATCH;
    }

    @Override
    public Target getTarget() {
        return target;
    }

    public void reset() {
        this.target = difficultyLevel.create();
    }
}
