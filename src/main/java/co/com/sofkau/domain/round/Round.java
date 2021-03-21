package co.com.sofkau.domain.round;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.events.RoundCreated;
import co.com.sofkau.domain.game.values.Forecast;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.events.FirstStageStarted;
import co.com.sofkau.domain.round.events.ForeCastCompared;
import co.com.sofkau.domain.round.events.RolledDice;
import co.com.sofkau.domain.round.events.RoundStarted;
import co.com.sofkau.domain.round.values.DiceFace;
import co.com.sofkau.domain.round.values.DiceId;
import co.com.sofkau.domain.round.values.Pot;
import co.com.sofkau.domain.round.values.StageId;

import java.util.*;
import java.util.stream.Collectors;

public class Round extends AggregateEvent<RoundId> {

    protected GameId gameId;
    protected RoundId roundId;
    protected Set<Person> players;
    protected StageId stageId;
    protected Map<DiceId, Dice> dices;
    protected Integer countStage;
    protected Pot pot;

    public Round(RoundId entityId) {
        super(entityId);
        subscribe(new RoundChange(this));
    }

    public Round(RoundId entityId, GameId gameId, Set<Person> players) {
        super(entityId);
        appendChange(new RoundCreated(gameId, entityId, players)).apply();
    }

    public static Round from(RoundId roundId, List<DomainEvent> events){
        var round = new Round(roundId);
        events.forEach(round::applyEvent);
        return round;
    }

    public void startRound (GameId gameId, RoundId roundId, Set<Person> players){
        appendChange(new RoundStarted(gameId, roundId, stageId, players));
    }

    public void startFirstStage(StageId stageId, Set<Person> players){
        appendChange(new FirstStageStarted(gameId, stageId, players));
    }

    public void rollDices() {
        var valuesDice = this.dices
                .values()
                .stream()
                .map(dice -> Map.of(dice.identity(), dice.values()))
                .collect(Collectors.toList());
        appendChange(new RolledDice(gameId, valuesDice)).apply();
    }

    public void compareForecast(Forecast forecast, List<DiceFace> diceValues){
        var  numberTimes = forecast.value().numberTimes();
        var diceface = forecast.value().faceDice();
        var foreCastCompared = new ForeCastCompared(numberTimes,diceface,diceValues,countStage);
        appendChange( ).apply();
    }



}
