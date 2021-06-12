package org.slipp.masil.games.infrastructures.events.jdbc;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.domains.highrow.HighLowPlayStart;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContextFactory;
import org.slipp.masil.games.domains.highrow.StartedHighLowPlay;
import org.slipp.masil.games.infrastructures.events.EventEnvelop;
import org.slipp.masil.games.infrastructures.events.sotre.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class JdbcEventStoreRepositoryTest {


    @Autowired
    EventStore eventStore;

    @Test
    void save() {
        HighLowPlayingContext context = HighLowPlayingContextFactory.DEFAULT.create(new HighLowPlayStart("Foo"));
        StartedHighLowPlay domainEvent = new StartedHighLowPlay(context);
        EventEnvelop eventEnvelop = EventEnvelop.of(domainEvent);
        eventStore.save(eventEnvelop);
    }
}