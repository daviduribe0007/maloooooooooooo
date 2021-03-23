package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.values.Forecast;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.Round;
import co.com.sofkau.domain.round.events.FirstStageStarted;
import co.com.sofkau.domain.round.values.DiceFace;

import java.util.ArrayList;
import java.util.List;

public class ValidateForCastUseCase extends UseCase<TriggeredEvent<FirstStageStarted>, ResponseEvents>  {
    @Override
    public void executeUseCase(TriggeredEvent<FirstStageStarted> firstStageStartedTriggeredEvent) {

        var event = firstStageStartedTriggeredEvent.getDomainEvent();
        var roundId = RoundId.of(event.aggregateRootId());

        var round = Round.from(RoundId.of(roundId.value()), retrieveEvents());
        var forecast = new Forecast();
        List<DiceFace> lista = new ArrayList<>();
        round.compareForecast(forecast,lista);
        emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));

    }
}
