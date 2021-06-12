package org.slipp.masil.games.domains.highrow;


import org.slipp.masil.games.domains.PlayState;

public class HighLowPlayService {

    private HighLowJudge judge;
    private final HighLowPlayingContextFactory contextFactory;
    private final HighLowPlayingContextRepository contextRepository;

    public HighLowPlayService(HighLowJudge judge, HighLowPlayingContextRepository contextRepository) {
        this(judge, HighLowPlayingContextFactory.DEFAULT, contextRepository);
    }

    public HighLowPlayService(HighLowJudge judge, HighLowPlayingContextFactory contextFactory, HighLowPlayingContextRepository contextRepository) {
        this.judge = judge;
        this.contextFactory = contextFactory;
        this.contextRepository = contextRepository;
    }

    public Long start(StartHighLowPlay command) {
        HighLowPlayingContext context = contextFactory.create(command);
        context.start();
        contextRepository.save(context);
        return context.getId();
    }
    // TODO 도메인 상으로 내려야 한다.
    public void stop(StopHighLowPlay command) {
        HighLowPlayingContext context = contextRepository.findById(command.getContextId());
        if (context.getState().equals(PlayState.ENDED)){
            throw new IllegalStateException("already ended game");
        }

        context.stop();
        contextRepository.save(context);
    }

    public HighLowPlayingResult play(GuessHighLowNumber command) {
        HighLowPlayingContext context = contextRepository.findById(command.getContextId());
        HighLowJudgement judgement = this.judge.judge(command.getGuessNumber());
        if (judgement == HighLowJudgement.MATCH) {
            context.match();
            contextRepository.save(context);
        }
        return new HighLowPlayingResult(command.getContextId(), judgement);
    }
}
