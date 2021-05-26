package org.slipp.masil.games.domains.highrow;

import org.springframework.data.repository.CrudRepository;

public interface HighLowPlayRepository extends CrudRepository<HighLowPlay, PlayId> {
	HighLowPlay findByPlayId(PlayId id);

}
