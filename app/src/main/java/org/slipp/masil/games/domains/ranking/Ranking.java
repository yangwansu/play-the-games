package org.slipp.masil.games.domains.ranking;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.units.qual.C;

import java.util.*;


@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Ranking {

    public static Ranking of(RankingId id, int sizeOfTop) {
        return new Ranking(id, sizeOfTop, new ArrayList<>());
    }

    private RankingId id;

    private int sizeOfTop;

    private List<PlayInfo> infos;


    public Ranking refresh(PlayInfo newInfo) {
        List<PlayInfo> newRanks = new ArrayList<>(infos);
        newRanks.add(newInfo);
        return new Ranking(getId(), sizeOfTop, newRanks);
    }

    public PlayInfo top(int topN) {
        List<PlayInfo> ranks = getInfos();
        ArrayList<PlayInfo> sorting = new ArrayList<>(ranks);
        sorting.sort((p0, p1) -> p1.getScore().getValue() - p0.getScore().getValue());
        if(getInfos().size() >= topN) {
            return sorting.get(topN-1);
        }else {
            return PlayInfo.NONE_PLAY_INFO;
        }
    }
}
