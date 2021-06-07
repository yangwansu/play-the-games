package org.slipp.masil.games.domains.highrow;

import org.slipp.masil.games.infrastructures.events.DomainEvent;

public class StoppedHighLowPlay extends AbstractHighLowPlayEvent<HighLowPlayingContext> implements DomainEvent {

    public StoppedHighLowPlay(HighLowPlayingContext source) {
        super(source);
    }
}
