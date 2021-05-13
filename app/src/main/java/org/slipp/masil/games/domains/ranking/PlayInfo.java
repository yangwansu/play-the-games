package org.slipp.masil.games.domains.ranking;

import lombok.Getter;
import lombok.Value;
import org.slipp.masil.games.domains.Score;

import java.time.LocalDateTime;

@Getter
@Value(staticConstructor = "of")
public class PlayInfo {

    public static final PlayInfo NONE_PLAY_INFO = PlayInfo.of(null,"--", Score.of(-1), null);

    GameRef gameRef;
    String userName;
    Score score;
    LocalDateTime at;
}
