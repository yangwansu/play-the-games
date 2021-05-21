package org.slipp.masil.games.domains.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.AbstractBaseJdbcTestConfig;
import org.slipp.masil.games.domains.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slipp.masil.games.domains.ranking.RankingItem.NONE_RANK_ITEM;
import static org.slipp.masil.games.domains.ranking.RankingItem.of;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringJUnitConfig
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class RankingRepositoryTest {


    public static class Config extends AbstractBaseJdbcTestConfig {
        @Override
        protected String[] getSql() {
            return new String[]{
                    "CREATE TABLE IF NOT EXISTS RANKING(id bigint primary key, size_of_top integer, version bigint)",
                    "CREATE TABLE IF NOT EXISTS RANKING_ITEM(ranking_id bigint, ranking_key integer, user_name varchar(100), score integer, ranked_at timestamp)"
            };
        }

        @Override
        protected List<Object> getConverters() {
            return Arrays.asList(
                    RankingIdToLong.INSTANCE, LongToRankingId.INSTANCE,
                    ScoreToInteger.INSTANCE, IntegerToScore.INSTANCE
            );
        }
    }


    @WritingConverter
    public enum RankingIdToLong implements Converter<RankingId, Long> {
        INSTANCE;

        @Override
        public Long convert(RankingId source) {
            return source.getGameId().getId();
        }
    }

    @ReadingConverter
    public enum LongToRankingId implements Converter<Long, RankingId> {
        INSTANCE;

        @Override
        public RankingId convert(Long source) {
            return RankingId.of(source);
        }
    }

    @WritingConverter
    public enum ScoreToInteger implements Converter<Score, Integer> {
        INSTANCE;

        @Override
        public Integer convert(Score source) {
            return source.getValue();
        }
    }

    @ReadingConverter
    public enum IntegerToScore implements Converter<Integer, Score> {
        INSTANCE;

        @Override
        public Score convert(Integer source) {
            return Score.of(source);
        }
    }

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
        Ranking find = repository.findById(saved.getId()).orElse(null);

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

        Ranking find = repository.findById(saved.getId()).orElse(null);

        assert find != null;

        assertThat(find.top(1)).isEqualTo(item);
        assertThat(find.top(2)).isEqualTo(NONE_RANK_ITEM);
        assertThat(find.top(3)).isEqualTo(NONE_RANK_ITEM);


    }
}
