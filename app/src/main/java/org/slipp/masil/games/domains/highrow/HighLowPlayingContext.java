package org.slipp.masil.games.domains.highrow;

import lombok.Getter;
import org.slipp.masil.games.domains.PlayState;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.slipp.masil.games.domains.PlayState.*;

public class HighLowPlayingContext extends AbstractAggregateRoot<HighLowPlayingContext> {

    public static final Long INIT_VERSION = null;
    @Id
    @Getter
    private final Long id;
    @Getter
    @Version
    private final Long version;
    @Getter
    private GameId gameId;
    @Getter
    private String userName;
    @Getter
    private LocalDateTime startAt;
    @Getter
    private PlayState state;
    @Getter
    private Score score;

    private HighLowPlayingContext(Long id,
                                  GameId gameId, String userName, LocalDateTime startAt, PlayState state, Score score,
                                  Long version) {
        this.id = id;
        if (Objects.isNull(gameId) && Objects.isNull(userName)) {
            throw new IllegalStateException(" is invalid");
        }
        setGameId(gameId);
        setUserName(userName);
        setStartAt(startAt);
        setState(state);
        setScore(score);
        this.version = version;
    }

    @Deprecated
    public static HighLowPlayingContext by(GameId gameId, String userName, LocalDateTime startAt) {
        return new HighLowPlayingContext(null, gameId, userName, startAt, INIT, Score.of(0), INIT_VERSION);
    }

    public static HighLowPlayingContext by(GameId gameId, String userName) {
        return by(gameId, userName, LocalDateTime.now());
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

    private void setState(PlayState state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException("state is invalid");
        }

        if(isOn() && state == ON_GAME) {
            throw new IllegalStateException();
        }

        if(! isOn() && state == ENDED) {
            throw new IllegalStateException();
        }

        if(isOff() && state == ON_GAME) {
            throw new IllegalStateException();
        }

        if(isOff() && state == ENDED) {
            throw new IllegalStateException();
        }
        this.state = state;
    }

    private void setScore(Score score) {
        if (Objects.isNull(score) || score.isValid() == false) {
            throw new IllegalArgumentException("score is invalid");
        }
        this.score = score;
    }

    public void start() {
        setState(ON_GAME);
        andEvent(new HighLowPlayStarted(this));
    }

    public void stop() {
        if (getState().equals(PlayState.ENDED)) {
            throw new IllegalStateException("already ended game");
        }
        this.setState(ENDED);
        andEvent(new HighLowPlayStopped(this));
    }

    public void match() {
        this.setState(ENDED);
        andEvent(new HighLowPlayMatched(this));
    }

    public void tryPlay() {
        if(!isOn()){
            throw new IllegalStateException();
        }
        this.setScore(score.plus());
    }

    public boolean isOn() {
        return ON_GAME == getState();
    }

    public boolean isOff() {
        return ENDED == getState();
    }
}
