package co.com.sofkau.domain.round.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.round.values.StageId;

import java.util.Set;

public class FirstStageStarted extends DomainEvent {
    private final GameId gameId;
    private final StageId stageId;
    private final Set<Person> players;

    public FirstStageStarted(GameId gameId, StageId stageId, Set<Person> players) {
        super("ddd-game.round.firststarted");
        this.gameId = gameId;
        this.stageId = stageId;
        this.players = players;
    }

    public GameId getGameId() {
        return gameId;
    }

    public StageId getStageId() {
        return stageId;
    }

    public Set<Person> getPlayers() {
        return players;
    }
}


