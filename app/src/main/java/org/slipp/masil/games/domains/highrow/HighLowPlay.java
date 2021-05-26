package org.slipp.masil.games.domains.highrow;

import lombok.Getter;
import org.slipp.masil.games.domains.HighLowResultOfTurn;
import org.slipp.masil.games.domains.HighLowTurn;
import org.slipp.masil.games.domains.PlayState;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.slipp.masil.games.domains.HighLowResultOfTurn.*;
import static org.slipp.masil.games.domains.PlayState.ENDED;
import static org.slipp.masil.games.domains.PlayState.ON_GAME;

public class HighLowPlay {

    public static final Long INIT_VERSION = null;
    @Id
    @Getter
    private Long id;
    @Getter
    private GameId gameId;
    @Getter
    private String userName;
    @Getter
    private LocalDateTime startAt;
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

    public static HighLowPlay by(GameId gameId, String userName, LocalDateTime startAt, int target) {
        return new HighLowPlay(null, gameId, userName, startAt, target, ON_GAME, Score.of(0), HighLowResultOfTurn.NONE, INIT_VERSION);
    }


    private HighLowPlay(Long id,
                        GameId gameId, String userName, LocalDateTime startAt, int target, PlayState state, Score score,
                        HighLowResultOfTurn highLowResultOfTurn, Long version) {
        this.id = id;
        if (Objects.isNull(gameId) && Objects.isNull(userName)){
            throw new IllegalStateException(" is invalid");
        }
        setGameId(gameId);
        setUserName(userName);
        setStartAt(startAt);
        setTarget(target);
        setState(state);
        setScore(score);
        setHighLowResultOfTurn(highLowResultOfTurn);
        this.version = version;
    }

    private void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }


    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setGameId(GameId gameId) {
        this.gameId = gameId;
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
        return new HighLowPlay(getId(), getGameId(), getUserName(), getStartAt(), getTarget(), state, getScore(), resultOfTurn, getVersion());
    }


    public HighLowPlay by(HighLowTurn turn) {
        if (target > turn.getGuess()) {
            return setState(ON_GAME, LOW);
        } else if (target < turn.getGuess()) {
            return setState(ON_GAME, HIGH);
        }
        return setState(ENDED, MATCHED);
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
