package co.com.sofkau.domain.game.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cash implements ValueObject<Float> {

    private final Float value;

    public Cash(Float value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Float value() {
        return value;
    }
}
