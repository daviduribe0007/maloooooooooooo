package co.com.sofkau.domain.game.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Forecast implements ValueObject<Forecast.Values> {
    private final Integer numberTimes;
    private final Integer faceDice;


    public Forecast(Integer numberTimes, Integer faceDice) {
        this.numberTimes = Objects.requireNonNull(numberTimes);
        this.faceDice = Objects.requireNonNull(faceDice);

        if ((numberTimes < 0) || (faceDice < 0)) {
            throw new IllegalArgumentException("Number of times or face of dice can't be negative");
        }
        if (numberTimes<3 && 6 < numberTimes){
            throw new IllegalArgumentException("Number of repetitions needs to be greater than 3 and smaller than 6");
        }

    }

    @Override
    public Values value() {
        return new Values() {
            @Override
            public Integer numberTimes() {
                return numberTimes;
            }

            @Override
            public Integer faceDice() {
                return faceDice;
            }
        };
    }

    public interface Values {
        Integer numberTimes();

        Integer faceDice();
    }
}



