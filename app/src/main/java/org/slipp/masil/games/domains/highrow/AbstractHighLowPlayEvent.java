package org.slipp.masil.games.domains.highrow;

import org.springframework.context.ApplicationEvent;

public abstract class AbstractHighLowPlayEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AbstractHighLowPlayEvent(Object source) {
        super(source);
    }
}
