package co.com.sofkau.domain.game;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.events.*;
import co.com.sofkau.domain.game.factory.PlayerFactory;
import co.com.sofkau.domain.game.values.*;

import java.util.List;
import java.util.Map;

public class Game extends AggregateEvent<GameId> {

    protected Map<Person, Player> players;
    protected RoundId roundId;
    protected Boolean gameStarted;

    public Game(GameId entityId, PlayerFactory playerFactory) {
        super(entityId);
        appendChange(new GameCreated(entityId)).apply();
        playerFactory.players().forEach(player -> createPlayer(player.identity(), player.name(), player.cash()));
    }

    public Game(GameId entityId) {
        super(entityId);
        subscribe(new GameChange(this));
    }


    public static Game from(GameId gameId, List<DomainEvent> events) {
        var game = new Game(gameId);
        events.forEach(game::applyEvent);
        return game;
    }

    public void startGame(Map<Person, Player> players) {
        appendChange(new GameStarted(players));
    }

    public void createRound() {
        var newRound = new RoundId();
        appendChange(new RoundCreated(this.entityId, newRound, players().keySet())).apply();
    }

    public void createPlayer(Person personId, Name name, Cash cash) {
        appendChange(new PlayerCreated(personId, name, cash));
    }

    public void decreaseCashPlayer(Person player, Cash cashDecrease) {
        appendChange(new CashPlayerDecreased(player, cashDecrease));
    }

    public Map<Person, Player> players(){
        return players;
    }

    public RoundId roundId() {
        return roundId;
    }

    public Boolean gameStarted() {
        return gameStarted;
    }

}
