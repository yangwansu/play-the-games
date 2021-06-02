package org.slipp.masil.games.domains.highrow;


import java.time.LocalDateTime;
import java.util.Optional;

public class HighLowPlayService {

    private HighLowPlayingContextRepository contextRepository;
    private HighLowJudge judge;

    public HighLowPlayService(HighLowPlayingContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }


    public Long start(HighLowPlayStart highLowStart) {
        HighLowPlayingContext context = HighLowPlayingContext.by(highLowStart.getGameId(), highLowStart.getUsername(), LocalDateTime.now(), 10);
        HighLowPlayingContext saved = contextRepository.save(context);
        return saved.getId();
    }

    public void stop(HighLowPlayStop highLowPlayStop) {
        Optional<HighLowPlayingContext> context = contextRepository.findById(highLowPlayStop.getContextId());
        context.ifPresent((c) -> {
            c.stop();
            contextRepository.save(c);
        });

    }

    public HighLowPlayingResult play(HighLowNumberGuess guess) {
        HighLowJudgement judgement = this.judge.judge(guess.getGuessNumber());
        if (judgement == HighLowJudgement.MATCH) {
            HighLowPlayingContext context = contextRepository.findById(guess.getContextId()).orElseThrow(IllegalStateException::new);
            context.match();
            contextRepository.save(context);
        }

        return new HighLowPlayingResult(guess.getContextId(), judgement);
    }

    public void setJudge(HighLowJudge judge) {
        this.judge = judge;
    }
}
