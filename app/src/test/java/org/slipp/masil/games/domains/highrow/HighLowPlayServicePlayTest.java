package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.slipp.masil.games.domains.highrow.HighLowJudgement.*;

@ExtendWith(MockitoExtension.class)
class HighLowPlayServicePlayTest {

    private static final long ANY_GUESS_NUMBER=0L;

    @Mock
    HighLowPlayingContextRepository repository;

    @Mock
    HighLowPlayingContext context;

    @Mock
    HighLowJudge judge;

    HighLowNumberGuess guessCommand;

    HighLowPlayService sut;

    @BeforeEach
    void setUp() {
        sut = new HighLowPlayService(repository);
        sut.setJudge(judge);
        given(repository.findById(ANY_GUESS_NUMBER)).willReturn(context);

        guessCommand = HighLowNumberGuess.of(context.getId(), ANY_GUESS_NUMBER);
    }

    @Test
    void play() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(HIGH);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(HIGH);
        verify(context, never()).match();
        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void play2() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(LOW);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(LOW);
        verify(context, never()).match();
        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void play3() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(MATCH);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(MATCH);
        verify(context).match();
        verify(repository).save(any(HighLowPlayingContext.class));
    }

}
