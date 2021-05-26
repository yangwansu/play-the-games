package org.slipp.masil.games.domains.highrow;

import org.slipp.masil.games.domains.game.GameId;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class PlayId {

	GameId gameId;
	String userName;

}
