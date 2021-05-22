package org.slipp.masil.games.domains.ranking;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.slipp.masil.games.domains.ranking.RankingItem.NONE_RANK_ITEM;


@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_=@PersistenceConstructor)
public class Ranking {

    public static final Long INIT_VERSION = null;

    @Deprecated
    public static Ranking of(RankingId id, int size) {
        return new Ranking(id, size, initItems(size), INIT_VERSION);
    }

    public static Ranking of(GameId id, int size) {
        return Ranking.of(RankingId.of(id), size);
    }

    private static ArrayList<RankingItem> initItems(int size) {
        ArrayList<RankingItem> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(NONE_RANK_ITEM);
        }
        return items;
    }

    @Id
    private final RankingId id;

    private final int size;

    @Column("RANKING_ID")
    private List<RankingItem> items;

    @Version
    private final Long version;

    public void refresh(RankingItem newInfo) {
        List<RankingItem> newRanks = new ArrayList<>(items);
        newRanks.add(newInfo);
        List<RankingItem> sorted = newRanks.stream().sorted().collect(Collectors.toList());

        items = sorted.subList(0, size);
    }

    public RankingItem top(int topN) {
        return items.get(arrayIndex(topN));
    }

    private int arrayIndex(int topN) {
        return topN - 1;
    }
}
