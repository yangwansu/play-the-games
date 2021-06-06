package org.slipp.masil.games.infrastructures.events;

import org.junit.jupiter.api.Test;
import org.slipp.masil.games.application.HighLowApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HighLowPlayingContextDomainEventTest {

    @Autowired
    HighLowApplicationService highLowApplicationService;

    @Test
    void name() {
        highLowApplicationService.start();
    }
}