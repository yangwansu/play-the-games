package org.slipp.masil.games.domains.highrow;


import java.time.LocalDateTime;

public class HighLowPlayService {

    private final HighLowPlayingContextRepository contextRepository;
    private HighLowJudge judge;

    public HighLowPlayService(HighLowJudge judge, HighLowPlayingContextRepository contextRepository) {
        this.judge = judge;
        this.contextRepository = contextRepository;
    }

    public Long start(HighLowPlayStart highLowStart) {
        HighLowPlayingContext context = HighLowPlayingContext.by(highLowStart.getGameId(), highLowStart.getUsername(),
                LocalDateTime.now(), 10);
        HighLowPlayingContext saved = contextRepository.save(context);
        return saved.getId();
    }

    public void stop(HighLowPlayStop highLowPlayStop) {
        HighLowPlayingContext context = contextRepository.findById(highLowPlayStop.getContextId());
        context.stop();
        contextRepository.save(context);
    }

    public HighLowPlayingResult play(HighLowNumberGuess guess) {
        HighLowPlayingContext context = contextRepository.findById(guess.getContextId());
        HighLowJudgement judgement = this.judge.judge(guess.getGuessNumber());
        if (judgement == HighLowJudgement.MATCH) {
            context.match();
            contextRepository.save(context);
        }
        return new HighLowPlayingResult(guess.getContextId(), judgement);
    }

}
