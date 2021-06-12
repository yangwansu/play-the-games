package org.slipp.masil.games.infrastructures.listeners;

import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.springframework.context.ApplicationEvent;

public class RankingRefreshEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RankingRefreshEvent(HighLowPlayingContext source) {
        super(source);
    }

    public HighLowPlayingContext getContext() {
        return (HighLowPlayingContext) getSource();
    }
}
