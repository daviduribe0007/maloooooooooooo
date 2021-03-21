package co.com.sofkau.domain.game.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;


public class PlayerCreated extends DomainEvent {

    private final Person person;
    private final Name name;
    private final Cash cash;

    public PlayerCreated(Person person, Name name, Cash cash) {
        super("ddd-game.game.playercreated");
        this.person = person;
        this.name = name;
        this.cash = cash;
    }

    public Person getPerson() {
        return person;
    }

    public Name getName() {
        return name;
    }

    public Cash getCash(){
        return cash;
    }

}
