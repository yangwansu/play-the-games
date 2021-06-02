package org.slipp.masil.games.domains.ranking;

import lombok.Getter;
import org.slipp.masil.games.domains.game.GameId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.slipp.masil.games.domains.ranking.RankingItem.NONE_RANK_ITEM;


@Getter
public class Ranking {

    public static final Long INIT_VERSION = null;
    @Id
    private RankingId id;
    private int size;
    @Column("RANKING_ID")
    private List<RankingItem> items;
    @Version
    private Long version;

    @PersistenceConstructor
    private Ranking(RankingId id, int size, List<RankingItem> items, Long version) {
        setId(id);
        setSize(size);
        setItems(items);
        setVersion(version);
    }

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

    //guard
    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("the size of ranking must be greater than zero.");
        }
        this.size = size;
    }

    //guard
    private void setId(RankingId id) {
        if (id == null || id.getGameId() == null || id.getGameId().getId() == null) {
            throw new IllegalArgumentException("id is invalid : " + id);
        }
        this.id = id;
    }

    private void setVersion(Long version) {
        if (version != null && version < 0) {
            throw new IllegalArgumentException();
        }
        this.version = version;
    }

    public void refresh(RankingItem newInfo) {
        List<RankingItem> newRanks = new ArrayList<>(getItems());
        newRanks.add(newInfo);
        List<RankingItem> sorted = newRanks.stream().sorted().collect(Collectors.toList());

        setItems(sorted.subList(0, size));
    }

    public RankingItem top(int topN) {
        return getItems().get(arrayIndex(topN));
    }

    private int arrayIndex(int topN) {
        return topN - 1;
    }

    public List<RankingItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void setItems(List<RankingItem> items) {
        if (items.size() != getSize()) {
            throw new IllegalArgumentException();
        }
        this.items = items;
    }
}
