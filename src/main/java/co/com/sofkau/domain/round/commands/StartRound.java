package co.com.sofkau.domain.round.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.game.values.RoundId;
import java.util.Set;

public class StartRound implements Command {

    private GameId gameId;
    private RoundId roundId;
    private Set<Person> players;

    public StartRound(GameId gameId, RoundId roundId, Set<Person> players) {
        this.gameId = gameId;
        this.roundId = roundId;
        this.players = players;
    }

    public GameId gameId() {
        return gameId;
    }

    public RoundId roundId() {
        return roundId;
    }

    public Set<Person> players(){
        return players;
    }

}
