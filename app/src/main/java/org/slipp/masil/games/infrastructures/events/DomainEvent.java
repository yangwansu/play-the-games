package org.slipp.masil.games.infrastructures.events;

import java.time.LocalDateTime;

public interface DomainEvent {

    Object getSource();

    default Object getAggregateRoot() {
        return getSource();
    }

    LocalDateTime getOccurredAt();
}
