package co.com.sofkau.domain.game.values;

import co.com.sofka.domain.generic.Identity;

public class GameId extends Identity {

    private GameId(String uid) {
        super(uid);
    }

    public GameId () {}

    public static GameId of(String uid) {
        return new GameId(uid);
    }
}
