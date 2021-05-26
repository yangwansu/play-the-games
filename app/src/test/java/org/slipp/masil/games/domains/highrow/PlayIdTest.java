package org.slipp.masil.games.domains.highrow;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.game.GameId;

class PlayIdTest {

	PlayId sut;

	@Test
	void CreatePlayId() {

		assertThatThrownBy(() -> PlayId.of(null, "Mike"))
			.isInstanceOf(NullPointerException.class)
			.hasMessageContaining("gameId must not be null");

		assertThatThrownBy(() -> PlayId.of(GameId.of(1L), null))
			.isInstanceOf(NullPointerException.class)
			.hasMessageContaining("username must not be null");

		sut = PlayId.of(GameId.of(1L), "Len");

		assertThat(sut).isNotNull();
		assertThat(sut.getGameId()).isEqualTo(GameId.of(1L));
		assertThat(sut.getUserName()).isEqualTo("Len");
	}
}
