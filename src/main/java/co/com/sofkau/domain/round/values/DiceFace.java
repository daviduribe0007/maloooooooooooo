package co.com.sofkau.domain.round.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class DiceFace implements ValueObject<Integer> {
    private final Integer value;

    public DiceFace(Integer value){
        this.value = Objects.requireNonNull(value, "The value of the face is required");
        if (value < 0 || value > 6) {
            throw new IllegalArgumentException("Value is out of range allowed");
        }
    }

    @Override
    public Integer value() {
        return value;
    }
}
