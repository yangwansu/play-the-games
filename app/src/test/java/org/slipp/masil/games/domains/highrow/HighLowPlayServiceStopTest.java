package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slipp.masil.games.domains.PlayState;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//TODO Exit 와 stop 의 차이 - playing 종료 | game 종료
//TODO stop 시 context 를 찾지 못했을때
@ExtendWith(MockitoExtension.class)
public class HighLowPlayServiceStopTest {

    private final static long ANY_CONTEXT_ID = 1L;

    HighLowPlayService sut;

    @Mock
    HighLowPlayingContextRepository repository;

    @Mock
    HighLowPlayingContext context;

    @Mock
    HighLowJudge judge;


    @BeforeEach
    void setUp() {
        sut = new HighLowPlayService(judge, repository);
    }

    StopHighLowPlay command = new StopHighLowPlay(1L, "userName");

    @Test
    void stop_under_playing() {
        given(repository.findById(1L)).willReturn(context);
        given(context.getState()).willReturn(PlayState.ON_GAME);

        sut.stop(command);

        InOrder exitInOrder = inOrder(context, repository);
        exitInOrder.verify(repository).findById(command.getContextId());
        exitInOrder.verify(context).stop();
        exitInOrder.verify(repository).save(context);
    }

    @Test
    void stop_when_already_playing_is_over() {
        given(repository.findById(1L)).willReturn(context);
        given(context.getState()).willReturn(PlayState.ENDED);

        assertThatThrownBy(() -> sut.stop(command)).isInstanceOf(IllegalStateException.class);

        InOrder exitInOrder = inOrder(context, repository);
        exitInOrder.verify(repository).findById(command.getContextId());
        exitInOrder.verify(repository, never()).save(context);
    }
}
