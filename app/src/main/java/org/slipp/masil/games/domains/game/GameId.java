package org.slipp.masil.games.domains.game;

import lombok.Value;

@Value(staticConstructor = "of")
public class GameId {
    Long id;
}
