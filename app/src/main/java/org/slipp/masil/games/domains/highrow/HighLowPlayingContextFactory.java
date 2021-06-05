package org.slipp.masil.games.domains.highrow;

import java.time.LocalDateTime;

public interface HighLowPlayingContextFactory {

    HighLowPlayingContextFactory DEFAULT = new HighLowPlayingContextFactory() {
        @Override
        public HighLowPlayingContext create(HighLowPlayStart command) {
            return HighLowPlayingContextFactory.super.create(command);
        }
    };


    default HighLowPlayingContext create(HighLowPlayStart command) {
        return HighLowPlayingContext.by(command.getGameId(), command.getUsername(),
                LocalDateTime.now(), 10);
    }
}
