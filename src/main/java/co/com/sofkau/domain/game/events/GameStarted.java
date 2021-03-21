package co.com.sofkau.domain.game.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.Player;
import co.com.sofkau.domain.game.values.Person;

import java.util.Map;

public class GameStarted extends DomainEvent {

    private final Map<Person,Player> players;

    public GameStarted(Map<Person,Player> players) {
        super("ddd-game.started");
        this.players = players;
    }

    public Map<Person,Player> getPlayers() {
        return players;
    }


}
