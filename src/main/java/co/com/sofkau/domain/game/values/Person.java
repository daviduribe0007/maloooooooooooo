package co.com.sofkau.domain.game.values;

import co.com.sofka.domain.generic.Identity;

public class Person extends Identity {
    private Person(String dni) {
        super(dni);
    }

    public Person() {
    }

    public static Person of(String dni) {
        return new Person(dni);
    }

}
