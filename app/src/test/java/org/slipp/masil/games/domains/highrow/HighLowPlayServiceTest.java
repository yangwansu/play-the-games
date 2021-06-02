package org.slipp.masil.games.domains.highrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

//TODO start command 가 널 일때
//TODO stop 시 context 를 찾지 못했을때
@ExtendWith(MockitoExtension.class)
class HighLowPlayServiceTest {

    @Mock
    HighLowPlayingContextRepository repository;

    @Mock
    HighLowPlayingContext context;

    HighLowPlayService sut;

    @BeforeEach
    void setUp() {
        sut = new HighLowPlayService(repository);
        given(repository.findById(1L)).willReturn(Optional.ofNullable(context));
        given(repository.save(any(HighLowPlayingContext.class))).willReturn(context);
    }

    @Test
    void start() {
        given(context.getId()).willReturn(1L); //TODO  명시적이지 않음

        HighLowPlayStart highLowStart = new HighLowPlayStart("Foo");
        Long contextId = sut.start(highLowStart);

        assertThat(contextId).isNotNull();
        verify(repository).save(any(HighLowPlayingContext.class));
    }


    @Test
    void stop() {
        HighLowPlayStop highLowPlayStop = new HighLowPlayStop(1L);
        sut.stop(highLowPlayStop);

        InOrder inOrder = inOrder(context, repository);
        inOrder.verify(repository).findById(highLowPlayStop.getContextId());
        inOrder.verify(context).stop();
        inOrder.verify(repository).save(any(HighLowPlayingContext.class));
    }
}