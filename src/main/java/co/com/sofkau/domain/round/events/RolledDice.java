package co.com.sofkau.domain.round.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.round.values.DiceFace;
import co.com.sofkau.domain.round.values.DiceId;

import java.util.List;
import java.util.Map;

public class RolledDice extends DomainEvent {

    private final GameId gameId;
    private final List<Map<DiceId, List<DiceFace>>> diceValues;

    public RolledDice(GameId gameId, List<Map<DiceId, List<DiceFace>>> diceValues) {
        super("ddd-game.round.rolleddice");
        this.gameId = gameId;
        this.diceValues = diceValues;
    }

    public GameId gameId() {
        return gameId;
    }

    public List<Map<DiceId, List<DiceFace>>> diceValues() {
        return diceValues;
    }

}
