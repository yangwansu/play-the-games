package org.slipp.masil.games.domains.highrow;

import org.slipp.masil.games.infrastructures.events.DomainEvent;

public class StartedHighLowPlay extends AbstractHighLowPlayEvent<HighLowPlayingContext> implements DomainEvent {

    public StartedHighLowPlay(HighLowPlayingContext source) {
        super(source);
    }

}
