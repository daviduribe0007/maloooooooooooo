package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.Round;
import co.com.sofkau.domain.round.events.RoundStarted;

public class RollDicesUseCase extends UseCase<TriggeredEvent<RoundStarted>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<RoundStarted> roundStartedTriggeredEvent) {
        var event = roundStartedTriggeredEvent.getDomainEvent();
        var round = Round.from(RoundId.of(event.aggregateRootId()), retrieveEvents());

        round.rollDices();

        emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));
    }
}
