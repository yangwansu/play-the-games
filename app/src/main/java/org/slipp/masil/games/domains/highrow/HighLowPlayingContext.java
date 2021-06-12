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

import static org.slipp.masil.games.domains.PlayState.ENDED;
import static org.slipp.masil.games.domains.PlayState.ON_GAME;

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
    private int target;
    @Getter
    private PlayState state;
    @Getter
    private Score score;

    private HighLowPlayingContext(Long id,
                                  GameId gameId, String userName, LocalDateTime startAt, int target, PlayState state, Score score,
                                  Long version) {
        this.id = id;
        if (Objects.isNull(gameId) && Objects.isNull(userName)) {
            throw new IllegalStateException(" is invalid");
        }
        setGameId(gameId);
        setUserName(userName);
        setStartAt(startAt);
        setTarget(target);
        setState(state);
        setScore(score);
        this.version = version;
    }

    public static HighLowPlayingContext by(GameId gameId, String userName, LocalDateTime startAt, int target) {
        return new HighLowPlayingContext(null, gameId, userName, startAt, target, ON_GAME, Score.of(0), INIT_VERSION);
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
        if (target < 0) {
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

    public void start() {
        andEvent(new HighLowPlayStarted(this));
    }

    public void stop() {
        this.setState(ENDED);
        andEvent(new HighLowPlayStopped(this));
    }

    public void match() {
        andEvent(new HighLowPlayMatched(this));
    }

}
