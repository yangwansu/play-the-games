package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class HighLowPlayingContextRepositoryTest {

	@Autowired
	HighLowPlayingContextRepository repository;

	HighLowPlayingContext play;

	@Test
	void saveAndFind() {

		int target = 10;
		GameId gameId = GameId.of(1L);
		String userName = "Len";
		play = HighLowPlayingContext.by(gameId, userName, LocalDateTime.now(), target);

		HighLowPlayingContext save = repository.save(play);
		HighLowPlayingContext find = repository.findById(save.getId()).orElse(null);

		assertThat(find).isNotNull();
	}
}
