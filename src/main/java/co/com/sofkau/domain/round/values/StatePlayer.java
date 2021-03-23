package co.com.sofkau.domain.round.values;

import co.com.sofka.domain.generic.ValueObject;

public class StatePlayer  implements ValueObject<Boolean> {
    private final Boolean value;

    public StatePlayer(Boolean value) {
        this.value = value;
    }


    @Override
    public Boolean value() {
        return value;
    }
}
