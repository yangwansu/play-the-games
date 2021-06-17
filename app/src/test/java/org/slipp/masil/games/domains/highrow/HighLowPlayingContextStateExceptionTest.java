package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.game.GameId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HighLowPlayingContextStateExceptionTest {

    GameId gameId = GameId.of(1L);
    String userName = "Mike";
    HighLowPlayingContext sut;

    @BeforeEach
    void setUp() {
        sut = HighLowPlayingContext.by(gameId, userName);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a start command when the state is on")
    void on_and_start() {
        sut.start(); // on
        assertThat(sut.isOn()).isTrue();

        assertThatThrownBy(
                () -> sut.start()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a stop command when the state is not on")
    void init_and_stop() {
        assertThat(sut.isOn()).isFalse();

        assertThatThrownBy(
                () -> sut.stop()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a match command when the state is not on")
    void init_and_match() {
        assertThat(sut.isOn()).isFalse();

        assertThatThrownBy(
                () -> sut.match()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a tryPlay command when the state is not on")
    void init_and_tryPlay() {
        assertThat(sut.isOn()).isFalse();

        assertThatThrownBy(
                () -> sut.tryPlay()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a start command when the state is off")
    void off_and_start() {
        sut.start(); // on
        sut.stop(); // off
        assertThat(sut.isOff()).isTrue();

        assertThatThrownBy(
                () -> sut.start()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a stop command when the state is off")
    void off_and_stop() {
        sut.start(); // on
        sut.stop(); // off
        assertThat(sut.isOff()).isTrue();

        assertThatThrownBy(
                () -> sut.stop()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a match command when the state is off")
    void off_and_math() {
        sut.start(); // on
        sut.stop(); // off
        assertThat(sut.isOff()).isTrue();

        assertThatThrownBy(
                () -> sut.match()
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Throw an exception " +
            "if there is a tryPlay command when the state is off")
    void off_and_tryPlay() {
        sut.start(); // on
        sut.stop(); // off
        assertThat(sut.isOff()).isTrue();

        assertThatThrownBy(
                () -> sut.tryPlay()
        ).isInstanceOf(IllegalStateException.class);
    }
}
