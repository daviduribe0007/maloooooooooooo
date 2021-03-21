package co.com.sofkau.domain.game;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofkau.domain.game.events.GameCreated;
import co.com.sofkau.domain.game.events.GameStarted;
import co.com.sofkau.domain.game.events.RoundCreated;

import java.util.HashMap;


public class GameChange extends EventChange {
    public GameChange(Game game) {
        apply((GameCreated event) -> {
            game.gameStarted = Boolean.TRUE;
            game.players = new HashMap<>();
        });

        apply((GameStarted event) -> {
            if (Boolean.TRUE.equals(game.gameStarted)){
                throw new IllegalArgumentException("Game is already created");
            }
            game.gameStarted = Boolean.TRUE;
        });

        apply((RoundCreated event) ->  game.roundId = event.getRoundId());

    }

}
