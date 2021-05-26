package org.slipp.masil.games.domains.highrow;

import static org.slipp.masil.games.domains.HighLowResultOfTurn.*;
import static org.slipp.masil.games.domains.PlayState.*;

import java.util.Objects;

import org.slipp.masil.games.domains.HighLowResultOfTurn;
import org.slipp.masil.games.domains.HighLowTurn;
import org.slipp.masil.games.domains.PlayState;
import org.slipp.masil.games.domains.Score;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Embedded;

import lombok.Getter;

public class HighLowPlay {

    public static final Long INIT_VERSION = null;
    @Id
    private Long id;
    @Embedded.Empty
    private PlayId playId;
    @Getter
    private int target;
    @Getter
    private PlayState state;
    @Getter
    private Score score;
    @Getter
    private HighLowResultOfTurn highLowResultOfTurn;
    @Getter
    @Version
    private Long version;

    public static HighLowPlay by(PlayId playId, int target) {
        return new HighLowPlay(null, playId, target, ON_GAME, Score.of(0), HighLowResultOfTurn.NONE, INIT_VERSION);
    }

    @PersistenceConstructor
    public HighLowPlay(Long id, PlayId playId, int target, PlayState state, Score score,
        HighLowResultOfTurn highLowResultOfTurn, Long version) {
        this.id = id;
        setPlayId(playId);
        setTarget(target);
        setState(state);
        setScore(score);
        setHighLowResultOfTurn(highLowResultOfTurn);
        this.version = version;
    }

    private void setPlayId(PlayId playId) {
        if (Objects.isNull(playId) || Objects.isNull(playId.getGameId()) || Objects.isNull(playId.getUserName())){
            throw new IllegalStateException("playId is invalid");
        }
        this.playId = playId;
    }

    private void setTarget(int target) {
        if (target < 0){
            throw new IllegalStateException("target is invalid");
        }
        this.target = target;
    }

    public void setState(PlayState state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("state is invalid");
        }
        this.state = state;
    }

    public void setScore(Score score) {
        if (Objects.isNull(score) || score.isValid() == false) {
            throw new IllegalArgumentException("score is invalid");
        }
        this.score = score;
    }

    public void setHighLowResultOfTurn(HighLowResultOfTurn highLowResultOfTurn) {
        if (Objects.isNull(highLowResultOfTurn)) {
            throw new IllegalArgumentException("highLowResultOfTurn is invalid");
        }
        this.highLowResultOfTurn = highLowResultOfTurn;
    }

    private HighLowPlay setState(PlayState state, HighLowResultOfTurn resultOfTurn) {
        return new HighLowPlay(null, getId(), getTarget(), state, getScore(), resultOfTurn, version);
    }

    public HighLowPlay by(HighLowTurn turn) {
        if (target > turn.getGuess()) {
            return setState(ON_GAME, LOW);
        } else if (target < turn.getGuess()) {
            return setState(ON_GAME, HIGH);
        }
        return setState(ENDED, MATCHED);
    }

    public PlayId getId() {
        return playId;
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
