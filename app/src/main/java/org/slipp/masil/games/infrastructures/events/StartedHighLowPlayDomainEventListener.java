package org.slipp.masil.games.infrastructures.events;

import org.slipp.masil.games.domains.highrow.StartedHighLowPlay;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartedHighLowPlayDomainEventListener implements ApplicationListener<StartedHighLowPlay> {

    @Override
    public void onApplicationEvent(StartedHighLowPlay event) {
        System.out.println(event);
    }
}
