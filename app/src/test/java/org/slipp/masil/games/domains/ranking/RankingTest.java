package org.slipp.masil.games.domains.ranking;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.game.GameId;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.slipp.masil.games.domains.ranking.RankingItem.NONE_RANK_ITEM;

class RankingTest {

    GameId gameId = GameId.of(1L);

    //TODO 잘못된 값 을 넣었을때
    //TODO 순위를 정하는 전략은 어디에 있나?
    //TODO size 이 0이라면?
    //TODO top 에 값이 size 을 벗어나면 ?


    @Test
    void create() {

        int size = 1;
        final Ranking ranking = Ranking.of(gameId, size);

        assertThat(ranking.top(1)).isSameAs(NONE_RANK_ITEM);
        assertThatThrownBy(()->ranking.top(2)).isInstanceOf(IndexOutOfBoundsException.class);

        size = 2;
        final Ranking ranking2 = Ranking.of(gameId, size);
        assertThat(ranking2.top(1)).isSameAs(NONE_RANK_ITEM);
        assertThat(ranking2.top(2)).isSameAs(NONE_RANK_ITEM);
        assertThatThrownBy(()->ranking2.top(3)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void refresh() {
        int size = 4;
        Ranking ranking = Ranking.of(gameId, size);

        RankingItem item1 = RankingItem.of("wansu", Score.of(0), LocalDateTime.now());
        RankingItem item2 = RankingItem.of("wansu", Score.of(10), LocalDateTime.now());
        RankingItem item3 = RankingItem.of("wansu", Score.of(20), LocalDateTime.now());
        RankingItem item4 = RankingItem.of("wansu", Score.of(30), LocalDateTime.now());

        ranking.refresh(item1);
        assertThat(ranking.top(1)).isEqualTo(item1);
        assertThat(ranking.top(2)).isEqualTo(NONE_RANK_ITEM);
        assertThat(ranking.top(3)).isEqualTo(NONE_RANK_ITEM);
        assertThat(ranking.top(4)).isEqualTo(NONE_RANK_ITEM);

        ranking.refresh(item2);
        assertThat(ranking.top(1)).isEqualTo(item2);
        assertThat(ranking.top(2)).isEqualTo(item1);
        assertThat(ranking.top(3)).isEqualTo(NONE_RANK_ITEM);
        assertThat(ranking.top(4)).isEqualTo(NONE_RANK_ITEM);

        ranking.refresh(item3);
        assertThat(ranking.top(1)).isEqualTo(item3);
        assertThat(ranking.top(2)).isEqualTo(item2);
        assertThat(ranking.top(3)).isEqualTo(item1);
        assertThat(ranking.top(4)).isEqualTo(NONE_RANK_ITEM);

        ranking.refresh(item4);
        assertThat(ranking.top(1)).isEqualTo(item4);
        assertThat(ranking.top(2)).isEqualTo(item3);
        assertThat(ranking.top(3)).isEqualTo(item2);
        assertThat(ranking.top(4)).isEqualTo(item1);
    }
}