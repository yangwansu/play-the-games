package org.slipp.masil.games.domains.ranking;

import lombok.Getter;
import lombok.Value;
import org.slipp.masil.games.domains.Score;

import java.time.LocalDateTime;

@Getter
@Value(staticConstructor = "of")
public class RankingItem implements Comparable<RankingItem>{

    public static final RankingItem NONE_RANK_ITEM = RankingItem.of("--", Score.of(-1), null);

    String userName;
    Score score;
    LocalDateTime rankedAt;


    @Override
    public int compareTo(RankingItem rankingItem) {
        return rankingItem.getScore().getValue() - this.getScore().getValue();
    }
}
