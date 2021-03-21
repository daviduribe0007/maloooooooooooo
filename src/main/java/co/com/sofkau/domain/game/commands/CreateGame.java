package co.com.sofkau.domain.game.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;

import java.util.Map;

public class CreateGame implements Command {
    private final Map<Person, Name> names;
    private final Map<Person, Cash> cashes;


    public CreateGame(Map<Person, Name> names, Map<Person, Cash> cashes) {
        this.names = names;
        this.cashes = cashes;
    }

    public Map<Person, Name> getNames(){
        return names;
    }

    public Map<Person, Cash> getCashes(){
        return cashes;
    }

}
