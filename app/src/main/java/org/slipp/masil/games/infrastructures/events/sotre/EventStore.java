package org.slipp.masil.games.infrastructures.events.sotre;

import org.slipp.masil.games.infrastructures.events.EventEnvelop;

public interface EventStore {

    void save(EventEnvelop anEventEnvelop);
}
