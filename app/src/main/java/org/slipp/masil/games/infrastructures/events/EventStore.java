package org.slipp.masil.games.infrastructures.events;

public interface EventStore {

    void save(EventEnvelop anEventEnvelop);
}
