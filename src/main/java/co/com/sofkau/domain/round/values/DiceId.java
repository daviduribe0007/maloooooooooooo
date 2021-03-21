package co.com.sofkau.domain.round.values;

import co.com.sofka.domain.generic.Identity;

public class DiceId extends Identity {

    private DiceId(Integer uid){
        super(uid.toString());
    }

    public DiceId(){}

    public static DiceId of(Integer uid) {
        return new DiceId(uid);
    }

}
