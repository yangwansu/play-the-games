package org.slipp.masil.games.domains.highrow;

import org.slipp.masil.games.infrastructures.events.DomainEvent;

public class MatchedHighLowPlay extends AbstractHighLowPlayEvent<HighLowPlayingContext> implements DomainEvent {

    public MatchedHighLowPlay(HighLowPlayingContext source) {
        super(source);
    }
}
