package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slipp.masil.games.domains.Judge;
import org.slipp.masil.games.domains.Target;

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
    Judge judge;

    GuessHighLowNumber guessCommand;

    HighLowPlayService sut;

    @BeforeEach
    void setUp() {
        sut = new HighLowPlayService(judge, repository);
        given(repository.findById(ANY_GUESS_NUMBER)).willReturn(context);

        guessCommand = GuessHighLowNumber.of(context.getId(), ANY_GUESS_NUMBER);
    }

    @Test
    void in_high_judgement_the_context_makes_nothing() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(HIGH);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(HIGH);
        verify(context, never()).match();
        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void in_low_judgement_the_context_makes_nothing() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(LOW);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(LOW);
        verify(context, never()).match();
        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void in_match_judgement_the_context_occurs_command_to_match() {
        given(judge.judge(guessCommand.getGuessNumber())).willReturn(MATCH);

        HighLowPlayingResult result = sut.play(guessCommand);

        assertThat(result.getJudgement()).isEqualTo(MATCH);
        verify(context).match();
        verify(repository).save(any(HighLowPlayingContext.class));
    }

}
