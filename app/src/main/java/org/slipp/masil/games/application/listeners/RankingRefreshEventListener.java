package org.slipp.masil.games.application.listeners;

import org.slipp.masil.games.application.RankingApplicationService;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RankingRefreshEventListener {

    RankingApplicationService rankingApplicationService;

    public RankingRefreshEventListener(RankingApplicationService rankingApplicationService) {
        this.rankingApplicationService = rankingApplicationService;
    }

    @Async
    @EventListener
    public void onApplicationEvent(RankingRefreshEvent event) {
        HighLowPlayingContext context = event.getContext();
        rankingApplicationService.refresh(context.getGameId(), context.getUserName(), context.getScore());
    }
}
