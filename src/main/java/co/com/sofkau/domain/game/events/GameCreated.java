package co.com.sofkau.domain.game.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.GameId;

public class GameCreated extends DomainEvent {

    private final GameId gameId;

    public GameCreated(GameId gameId) {
        super("ddd-game.game.created");
        this.gameId = gameId;
    }

    public GameId getGameId(){
        return gameId;
    }
}
