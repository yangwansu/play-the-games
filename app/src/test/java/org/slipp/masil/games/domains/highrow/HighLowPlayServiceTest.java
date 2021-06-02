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
import static org.mockito.Mockito.*;
import static org.slipp.masil.games.domains.highrow.HighLowJudgement.*;

//TODO start command 가 널 일때
//TODO stop 시 context 를 찾지 못했을때
//TODO 이미 stop 된 것을 stop 하려 할경우
// TODO contextId 를 아무나 넘길수 있다... 개똥이가 플레이하고 길동이 가 개똥이 컨택스트 아이디로 하면 어까지?
@ExtendWith(MockitoExtension.class)
class HighLowPlayServiceTest {

    private static final long ANY_GUESS_NUMBER=0L;
    public static final long ANY_CTX_ID = 1L;

    @Mock
    HighLowPlayingContextRepository repository;

    @Mock
    HighLowPlayingContext context;

    HighLowPlayService sut;

    @BeforeEach
    void setUp() {
        sut = new HighLowPlayService(repository);
    }

    @Test
    void start() {
        given(context.getId()).willReturn(ANY_CTX_ID);
        given(repository.save(any(HighLowPlayingContext.class))).willReturn(context);

        HighLowPlayStart highLowStart = new HighLowPlayStart("Foo");
        Long contextId = sut.start(highLowStart);

        assertThat(contextId).isEqualTo(ANY_CTX_ID);
        verify(repository).save(any(HighLowPlayingContext.class));
    }

    @Test
    void play() {
        HighLowJudge judge= mock(HighLowJudge.class);
        given(judge.judge(ANY_GUESS_NUMBER)).willReturn(HIGH);
        sut.setJudge(judge);

        HighLowPlayingResult result = sut.play(HighLowNumberGuess.of(context.getId(), ANY_GUESS_NUMBER));
        assertThat(result.getJudgement()).isEqualTo(HIGH);

        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void play2() {
        HighLowJudge judge= mock(HighLowJudge.class);
        given(judge.judge(ANY_GUESS_NUMBER)).willReturn(LOW);
        sut.setJudge(judge);

        HighLowPlayingResult result = sut.play(HighLowNumberGuess.of(context.getId(), ANY_GUESS_NUMBER));
        assertThat(result.getJudgement()).isEqualTo(LOW);

        verify(repository, never()).save(any(HighLowPlayingContext.class));
    }

    @Test
    void play3() {
        given(repository.findById(ANY_GUESS_NUMBER)).willReturn(context);
        HighLowJudge judge= mock(HighLowJudge.class);
        given(judge.judge(ANY_GUESS_NUMBER)).willReturn(MATCH);
        sut.setJudge(judge);

        HighLowPlayingResult result = sut.play(HighLowNumberGuess.of(context.getId(), ANY_GUESS_NUMBER));
        assertThat(result.getJudgement()).isEqualTo(MATCH);

        verify(repository).save(any(HighLowPlayingContext.class));
    }

        //TODO result 는 왜 리턴하느냐? high? low? matched! contextId,
    @Test
    void stop() {
        given(repository.findById(1L)).willReturn(context);
        given(repository.save(any(HighLowPlayingContext.class))).willReturn(context);

        HighLowPlayStop highLowPlayStop = new HighLowPlayStop(ANY_CTX_ID);
        sut.stop(highLowPlayStop);

        InOrder inOrder = inOrder(context, repository);
        inOrder.verify(repository).findById(highLowPlayStop.getContextId());
        inOrder.verify(context).stop();
        inOrder.verify(repository).save(any(HighLowPlayingContext.class));
    }
}
