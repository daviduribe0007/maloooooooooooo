package co.com.sofkau.domain.round.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Pot implements ValueObject<Float> {
    private final Float value;

    public Pot(Float value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Float value() {
        return value;
    }
}
