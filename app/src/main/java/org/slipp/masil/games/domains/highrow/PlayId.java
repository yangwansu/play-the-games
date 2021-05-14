package org.slipp.masil.games.domains.highrow;

import static java.util.Objects.*;

import lombok.Getter;

@Getter
public class PlayId {

	private GameRef gameRef;
	private UserName userName;

	private PlayId(GameRef gameRef, UserName userName) {
		setGameRef(gameRef);
		setUserName(userName);
	}

	public static PlayId of(GameRef gameRef, UserName userName){
		return new PlayId(gameRef, userName);
	}

	private void setUserName(UserName userName) {
		requireNonNull(userName, "username must not be null");
		this.userName = userName;
	}

	public void setGameRef(GameRef gameRef) {
		requireNonNull(gameRef, "gameId must not be null");
		this.gameRef = gameRef;
	}
}
