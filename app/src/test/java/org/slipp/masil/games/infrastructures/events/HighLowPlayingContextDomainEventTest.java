package org.slipp.masil.games.infrastructures.events;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.slipp.masil.games.application.HighLowApplicationService;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HighLowPlayingContextDomainEventTest {

    @Autowired
    HighLowApplicationService highLowApplicationService;

    @Autowired
    HighLowPlayingContextRepository repository;

    @Test
    void name() {
        highLowApplicationService.start();

        HighLowPlayingContext c = repository.findById(0L);
        System.out.println(c);
    }
}