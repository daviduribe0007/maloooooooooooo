package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.Round;
import co.com.sofkau.domain.round.events.RoundStarted;

public class StartFirstStageUseCase extends UseCase<TriggeredEvent<RoundStarted>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<RoundStarted> roundStartedTriggeredEvent) {
        var event = roundStartedTriggeredEvent.getDomainEvent();
        var roundId = RoundId.of(event.aggregateRootId());

        var round = Round.from(RoundId.of(roundId.value()), retrieveEvents());

        try {
            round.startFirstStage(event.getStageId(), event.getPlayers());
            emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(round.identity().value(), e.getMessage()));
        }
    }
}
