package co.com.sofkau.domain.game.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.game.values.RoundId;

import java.util.Set;

public class RoundCreated extends DomainEvent {
    private final GameId gameId;
    private final RoundId roundId;
    private final Set<Person> players;

    public RoundCreated(GameId gameId, RoundId roundId, Set<Person> players) {
        super("ddd-game.round.created");
        this.gameId = gameId;
        this.roundId = roundId;
        this.players = players;
    }

    public GameId getGameId() {return gameId;}

    public RoundId getRoundId() {
        return roundId;
    }

    public Set<Person> getPlayers() {
        return players;
    }

}
