package org.slipp.masil.games.domains.ranking;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.Score;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.ranking.PlayInfo.NONE_PLAY_INFO;

class RankingTest {

    GameRef gameRef = GameRef.of(1L);
    RankingId id = RankingId.of(gameRef);

    //TODO 잘못된 값 을 넣었을때
    //TODO 순위를 정하는 전략은 어디에 있나?
    //TODO sizeOfTop 이 0이라면?
    //TODO top 에 값이 sizeOfTop 을 벗어나면 ?


    @Test
    void create() {

        int sizeOfTop = 1;
        Ranking ranking = Ranking.of(id, sizeOfTop);

        assertThat(ranking.top(1)).isSameAs(NONE_PLAY_INFO);
        assertThat(ranking.top(2)).isSameAs(NONE_PLAY_INFO);
        assertThat(ranking.top(3)).isSameAs(NONE_PLAY_INFO);
        assertThat(ranking.top(9999)).isSameAs(NONE_PLAY_INFO);
    }

    @Test
    void refresh() {
        int sizeOfTop = 4;
        Ranking ranking = Ranking.of(id, sizeOfTop);

        PlayInfo info1 = PlayInfo.of(gameRef, "wansu", Score.of(0), LocalDateTime.now());
        PlayInfo info2 = PlayInfo.of(gameRef, "wansu", Score.of(10), LocalDateTime.now());
        PlayInfo info3 = PlayInfo.of(gameRef, "wansu", Score.of(20), LocalDateTime.now());
        PlayInfo info4 = PlayInfo.of(gameRef, "wansu", Score.of(30), LocalDateTime.now());

        Ranking refreshed = ranking.refresh(info1);
        assertThat(refreshed.top(1)).isEqualTo(info1);
        assertThat(refreshed.top(2)).isEqualTo(NONE_PLAY_INFO);
        assertThat(refreshed.top(3)).isEqualTo(NONE_PLAY_INFO);
        assertThat(refreshed.top(4)).isEqualTo(NONE_PLAY_INFO);

        refreshed = refreshed.refresh(info2);
        assertThat(refreshed.top(1)).isEqualTo(info2);
        assertThat(refreshed.top(2)).isEqualTo(info1);
        assertThat(refreshed.top(3)).isEqualTo(NONE_PLAY_INFO);
        assertThat(refreshed.top(4)).isEqualTo(NONE_PLAY_INFO);

        refreshed = refreshed.refresh(info3);
        assertThat(refreshed.top(1)).isEqualTo(info3);
        assertThat(refreshed.top(2)).isEqualTo(info2);
        assertThat(refreshed.top(3)).isEqualTo(info1);
        assertThat(refreshed.top(4)).isEqualTo(NONE_PLAY_INFO);

        refreshed = refreshed.refresh(info4);
        assertThat(refreshed.top(1)).isEqualTo(info4);
        assertThat(refreshed.top(2)).isEqualTo(info3);
        assertThat(refreshed.top(3)).isEqualTo(info2);
        assertThat(refreshed.top(4)).isEqualTo(info1);
    }
}