package co.com.sofkau.domain.game.factory;

import co.com.sofkau.domain.game.Player;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;

import java.util.HashSet;
import java.util.Set;

public class PlayerFactory {
    private final Set<Player> players;

    private PlayerFactory() {
        players = new HashSet<>();
    }

    public static PlayerFactory builder() {
        return new PlayerFactory();
    }

    public PlayerFactory newPlayer(Person personId, Name name, Cash cash) {
        players.add(new Player(personId, name, cash));
        return this;

    }

    public Set<Player> players() {
        return players;
    }

}
