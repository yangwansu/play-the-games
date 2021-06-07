package org.slipp.masil.games.domains.highrow;

import lombok.Getter;
import org.slipp.masil.games.infrastructures.events.DomainEvent;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public abstract class AbstractHighLowPlayEvent<T> extends ApplicationEvent implements DomainEvent {

    @Getter
    private T aggregateRoot;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AbstractHighLowPlayEvent(T source) {
        super(source);
        this.aggregateRoot = source;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        // TODO Zone
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(getTimestamp()),
                TimeZone.getDefault().toZoneId());
    }


}
