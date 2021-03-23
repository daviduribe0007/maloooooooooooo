package co.com.sofkau.domain.round;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofkau.domain.game.events.RoundCreated;
import co.com.sofkau.domain.round.events.ForeCastCompared;
import co.com.sofkau.domain.round.events.RolledDice;
import co.com.sofkau.domain.round.events.RoundStarted;
import co.com.sofkau.domain.round.values.DiceId;
import co.com.sofkau.domain.round.values.Pot;
import co.com.sofkau.domain.round.values.StageId;

import java.util.HashMap;
import java.util.HashSet;

public class RoundChange extends EventChange {
    public RoundChange(Round round) {

        apply((RoundCreated event) -> {
            round.gameId = event.getGameId();
            round.players = event.getPlayers();
            round.pot = new Pot(0F);
            round.dices = new HashMap<>();
        });

        apply((RoundStarted event) -> {
            round.stageId = new StageId();

            for (var i = 1; i < 7; i++) {
                round.dices.put(DiceId.of(i), new Dice(DiceId.of(i)));
            }
        });



        apply((RolledDice event) -> round.dices.values().forEach(Dice::rollDice));
        apply((ForeCastCompared event) -> round.compareForecast(round.forecast, round.diceValues));
    }
}
