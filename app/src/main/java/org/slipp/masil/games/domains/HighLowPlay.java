package org.slipp.masil.games.domains;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.slipp.masil.games.domains.HighLowResultOfTurn.*;
import static org.slipp.masil.games.domains.PlayState.*;
import static org.slipp.masil.games.domains.PlayState.ENDED;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HighLowPlay {
    private final String userName;
    private final int target;

    private PlayState state;
    private Score score;
    private HighLowResultOfTurn lastResultOfTurn;

    public static HighLowPlay by(String userName, int target) {
        return new HighLowPlay(userName, target, ON_GAME, Score.of(0), HighLowResultOfTurn.NONE);
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
        return new HighLowPlay(getUserName(), getTarget(), state, getScore(), resultOfTurn);
    }

}
