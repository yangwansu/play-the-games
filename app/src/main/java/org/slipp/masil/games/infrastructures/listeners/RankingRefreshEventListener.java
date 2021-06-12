package org.slipp.masil.games.infrastructures.listeners;

import org.slipp.masil.games.application.RankingApplicationService;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RankingRefreshEventListener implements ApplicationListener<RankingRefreshEvent> {

    RankingApplicationService rankingApplicationService;

    public RankingRefreshEventListener(RankingApplicationService rankingApplicationService) {
        this.rankingApplicationService = rankingApplicationService;
    }

    @Async
    @Override
    public void onApplicationEvent(RankingRefreshEvent event) {
        HighLowPlayingContext context = event.getContext();
        rankingApplicationService.refresh(context.getGameId(), context.getUserName(), context.getScore());
    }
}
