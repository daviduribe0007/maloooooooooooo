package co.com.sofkau.domain.round.values;

import co.com.sofka.domain.generic.Identity;

public class TurnId extends Identity {
    private TurnId(Integer uid) {
        super(uid.toString());
    }

    public TurnId() {
    }

    public TurnId of(Integer uid) {
        return new TurnId(uid);
    }
}
