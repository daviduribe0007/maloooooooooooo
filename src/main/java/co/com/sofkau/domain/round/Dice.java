package co.com.sofkau.domain.round;

import co.com.sofka.domain.generic.Entity;
import co.com.sofkau.domain.round.values.DiceFace;
import co.com.sofkau.domain.round.values.DiceId;

import java.util.ArrayList;
import java.util.List;

public class Dice extends Entity<DiceId> {

    private List<DiceFace> values;

    public Dice(DiceId entityId) {
        super(entityId);
        this.values = new ArrayList<>();
    }

    public void rollDice() {
        for (int i = 0; i < 7; i++){
            var diceFace = (int) ((Math.random()*6) + 1);
            values.add(new DiceFace(diceFace));
        }
    }

    public List<DiceFace> values() {
        return values;
    }

}
