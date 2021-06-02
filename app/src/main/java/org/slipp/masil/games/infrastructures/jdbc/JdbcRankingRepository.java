package org.slipp.masil.games.infrastructures.jdbc;

import org.slipp.masil.games.domains.ranking.Ranking;
import org.slipp.masil.games.domains.ranking.RankingId;
import org.slipp.masil.games.domains.ranking.RankingRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Ranking.class, idClass = RankingId.class)
public interface JdbcRankingRepository extends RankingRepository {

}
