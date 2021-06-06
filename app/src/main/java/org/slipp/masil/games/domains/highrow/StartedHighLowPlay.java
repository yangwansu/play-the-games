package org.slipp.masil.games.domains.highrow;

import org.springframework.context.ApplicationEvent;

public class StartedHighLowPlay extends ApplicationEvent {

    public StartedHighLowPlay(HighLowPlayingContext source) {
        super(source);
    }
}
