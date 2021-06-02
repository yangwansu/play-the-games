package org.slipp.masil.games.domains.highrow;


import java.time.LocalDateTime;
import java.util.Optional;

public class HighLowPlayService {

    private HighLowPlayingContextRepository contextRepository;

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
        context.ifPresent((c)->{
            c.stop();
            contextRepository.save(c);
        });


    }
}
