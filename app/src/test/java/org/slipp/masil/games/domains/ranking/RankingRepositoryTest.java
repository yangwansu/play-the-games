package org.slipp.masil.games.domains.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.ranking.RankingItem.NONE_RANK_ITEM;
import static org.slipp.masil.games.domains.ranking.RankingItem.of;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class RankingRepositoryTest {


    @Autowired
    RankingRepository repository;

    Ranking saved;

    @BeforeEach
    void setUp() {
        RankingId id = RankingId.of(1L);
        Ranking ranking = Ranking.of(id, 3);
        saved = repository.save(ranking);
    }

    @Test
    void saveAndFind() {
        Ranking find = repository.findById(saved.getId());

        assert find != null;

        assertThat(find)
                .hasFieldOrPropertyWithValue("id",saved.getId())
                .hasFieldOrPropertyWithValue("version", 0L);

        assertThat(find.getItems()).size().isEqualTo(3);

        assertThat(find.top(1)).isEqualTo(NONE_RANK_ITEM);
        assertThat(find.top(2)).isEqualTo(NONE_RANK_ITEM);
        assertThat(find.top(3)).isEqualTo(NONE_RANK_ITEM);

    }

    @Test
    void refreshAndUpdate() {

        RankingItem item = of("Wansu", Score.of(100), LocalDateTime.now());
        saved.refresh(item);
        repository.save(saved);

        Ranking find = repository.findById(saved.getId());

        assert find != null;

        assertThat(find.top(1)).isEqualTo(item);
        assertThat(find.top(2)).isEqualTo(NONE_RANK_ITEM);
        assertThat(find.top(3)).isEqualTo(NONE_RANK_ITEM);


    }
}
