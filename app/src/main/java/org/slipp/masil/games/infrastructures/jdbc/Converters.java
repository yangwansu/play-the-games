package org.slipp.masil.games.infrastructures.jdbc;

import org.slipp.masil.games.domains.Score;
import org.slipp.masil.games.domains.ranking.RankingId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class Converters {


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
}
