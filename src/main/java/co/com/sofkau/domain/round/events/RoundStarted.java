package co.com.sofkau.domain.round.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.values.StageId;

import java.util.Set;

public class RoundStarted extends DomainEvent {
    private final GameId gameId;
    private final RoundId roundId;
    private final StageId stageId;
    private final Set<Person> players;

    public RoundStarted(GameId gameId, RoundId roundId, StageId stageId, Set<Person> players) {
        super("ddd-game.roundstarted");
        this.gameId = gameId;
        this.roundId = roundId;
        this.stageId = stageId;
        this.players = players;
    }

    public GameId getGameId() {
        return gameId;
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public StageId getStageId() { return stageId; }

    public Set<Person> getPlayers() {
        return players;
    }

}
