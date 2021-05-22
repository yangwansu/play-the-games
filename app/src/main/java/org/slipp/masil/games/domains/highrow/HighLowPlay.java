package org.slipp.masil.games.domains.highrow;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slipp.masil.games.domains.HighLowResultOfTurn;
import org.slipp.masil.games.domains.HighLowTurn;
import org.slipp.masil.games.domains.PlayState;
import org.slipp.masil.games.domains.Score;

import static org.slipp.masil.games.domains.HighLowResultOfTurn.*;
import static org.slipp.masil.games.domains.PlayState.ENDED;
import static org.slipp.masil.games.domains.PlayState.ON_GAME;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HighLowPlay {
    private PlayId id;
    private final int target;

    private PlayState state;
    private Score score;
    private HighLowResultOfTurn lastResultOfTurn;

    public static HighLowPlay by(int target) {
        return new HighLowPlay(null, target, ON_GAME, Score.of(0), HighLowResultOfTurn.NONE);
    }

    public HighLowPlay by(HighLowTurn turn) {
        if (target > turn.getGuess()) {
            return setState(ON_GAME, LOW);
        } else if (target < turn.getGuess()) {
            return setState(ON_GAME, HIGH);
        }
        return setState(ENDED, MATCHED);

    }

    private HighLowPlay setState(PlayState state, HighLowResultOfTurn resultOfTurn) {
        return new HighLowPlay(getId(), getTarget(), state, getScore(), resultOfTurn);
    }

    //TODO Play, doTurn 은 어떻게 구현할 것인가?

    public void exit() {
        if (this.state.equals(ON_GAME)) {
            this.state = ENDED;
        } else if (this.state.equals(ENDED)) {
            throw new IllegalStateException("play has already ended");
        }
    }
}
