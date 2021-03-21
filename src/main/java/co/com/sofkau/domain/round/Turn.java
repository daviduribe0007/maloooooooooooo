package co.com.sofkau.domain.round;

import co.com.sofka.domain.generic.Entity;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.round.values.CompoundBet;
import co.com.sofkau.domain.round.values.TurnId;

public class Turn extends Entity<TurnId> {

    private final Person person;
    private final CompoundBet turnBet;

    public Turn(TurnId entityId, Person person, CompoundBet turnBet) {
        super(entityId);
        this.person = person;
        this.turnBet = turnBet;
    }

    public Person person() {
        return person;
    }

    public CompoundBet turnBet() {
        return turnBet;
    }

    public void requestBettingRound(){
        // TODO implement method
    }

}
