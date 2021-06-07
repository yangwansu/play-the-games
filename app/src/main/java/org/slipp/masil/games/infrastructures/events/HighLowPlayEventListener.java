package org.slipp.masil.games.infrastructures.events;

import org.slipp.masil.games.domains.highrow.AbstractHighLowPlayEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HighLowPlayEventListener implements ApplicationListener<AbstractHighLowPlayEvent> {

    @Override
    public void onApplicationEvent(AbstractHighLowPlayEvent event) {
        System.out.println(event);
    }
}
