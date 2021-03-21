package co.com.sofkau.domain.game.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.domain.game.Player;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;

import java.util.Map;

public class StartGame implements Command {

    private final GameId gameId;
    private final Map<Person, Player> players;


    public StartGame(GameId gameId,  Map<Person, Player> players) {
        this.gameId = gameId;
        this.players = players;
    }

    public GameId getGameId() {
        return gameId;
    }

    public  Map<Person, Player> getPlayers() {
        return players;
    }


}
