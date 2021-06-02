package org.slipp.masil.games.infrastructures.jdbc;

import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContextRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = HighLowPlayingContext.class, idClass = Long.class)
public interface JdbcHighLowPlayingContextRepository extends HighLowPlayingContextRepository {

}
