package org.slipp.masil.games.domains.highrow;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class HighLowPlayRepositoryTest {

	@Autowired
	HighLowPlayRepository repository;

	HighLowPlay play;

	@Test
	void saveAndFind() {
		PlayId playId = PlayId.of(GameId.of(1L), "Len");
		int target = 10;
		play = HighLowPlay.by(playId, target);
		HighLowPlay save = repository.save(play);

		HighLowPlay found = repository.findByPlayId(save.getId());
		assertThat(found.getId()).isEqualTo(playId);
		assertThat(found.getVersion()).isNotNull();
	}
}
