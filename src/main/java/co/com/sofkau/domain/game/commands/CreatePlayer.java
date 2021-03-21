package co.com.sofkau.domain.game.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.domain.game.values.Person;

public class CreatePlayer implements Command {

    private final Person person;
    private final String name;

    public CreatePlayer(Person person, String name) {
        this.person = person;
        this.name = name;
    }

    public Person person() {
        return person;
    }

    public String name() {
        return name;
    }
}
