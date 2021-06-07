package org.slipp.masil.games.infrastructures.events.jdbc;

import org.slipp.masil.games.infrastructures.events.EventStore;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = EventStore.class, idClass = Long.class)
public interface JdbcEventStoreRepository extends EventStore {
}
