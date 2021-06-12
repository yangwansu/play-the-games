package org.slipp.masil.games.domains.highrow;

import lombok.Getter;
import org.slipp.masil.games.infrastructures.events.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public abstract class AbstractHighLowPlayEvent<T>  implements DomainEvent<T> {

    @Getter
    private T aggregateRoot;

    /** use serialVersionUID from Spring 1.2 for interoperability. */
    private static final long serialVersionUID = 7099057708183571937L;

    @Getter
    /** System time when the event happened. */
    private final long timestamp;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AbstractHighLowPlayEvent(T source) {
        this.aggregateRoot = source;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public LocalDateTime getOccurredAt() {
        // TODO Zone
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(getTimestamp()),
                TimeZone.getDefault().toZoneId());
    }

    public T getAggregateRoot() {
        return this.aggregateRoot;
    }


}
