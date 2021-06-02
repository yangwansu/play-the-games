package org.slipp.masil.games.infrastructures.config;

import org.slipp.masil.games.infrastructures.jdbc.Converters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
public class DataJdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                Converters.RankingIdToLong.INSTANCE, Converters.LongToRankingId.INSTANCE,
                Converters.ScoreToInteger.INSTANCE, Converters.IntegerToScore.INSTANCE,
                Converters.GameIdToLong.INSTANCE, Converters.LongToGameId.INSTANCE
        ));
    }
}
