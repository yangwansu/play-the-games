package org.slipp.masil.games.infrastructures.events;

import java.time.LocalDateTime;

public interface DomainEvent<T> {


    T getAggregateRoot();

    LocalDateTime getOccurredAt();
}
