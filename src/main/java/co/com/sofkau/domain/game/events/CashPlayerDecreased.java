package co.com.sofkau.domain.game.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Person;

public class CashPlayerDecreased extends DomainEvent {
    private final Person player;
    private final Cash cash;

    public CashPlayerDecreased(Person player, Cash cash) {
        super("ddd-game.game.cashplayerdecreased");
        this.player = player;
        this.cash = cash;
    }

    public Person getPlayer() {
        return player;
    }

    public Cash getCash() {
        return cash;
    }
}
