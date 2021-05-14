package org.slipp.masil.games.domains.highrow;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayIdTest {

	PlayId sut;

	@Test
	void CreatePlayId() {

		assertThatThrownBy(() -> PlayId.of(null, UserName.of("Mike")))
			.isInstanceOf(NullPointerException.class)
			.hasMessageContaining("gameId must not be null");

		assertThatThrownBy(() -> PlayId.of(GameRef.of(1L), null))
			.isInstanceOf(NullPointerException.class)
			.hasMessageContaining("username must not be null");

		sut = PlayId.of(GameRef.of(1L), UserName.of("Len"));

		assertThat(sut).isNotNull();
		assertThat(sut.getGameRef()).isEqualTo(GameRef.of(1L));
		assertThat(sut.getUserName()).isEqualTo(UserName.of("Len"));
	}
}