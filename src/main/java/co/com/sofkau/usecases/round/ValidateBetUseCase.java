package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.values.Forecast;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.Round;
import co.com.sofkau.domain.round.commands.StartRound;
import co.com.sofkau.domain.round.events.FirstStageStarted;

public class ValidateBetUseCase extends UseCase<TriggeredEvent<FirstStageStarted>, ResponseEvents>  {
    @Override
    public void executeUseCase(TriggeredEvent<FirstStageStarted> firstStageStartedTriggeredEvent) {

        var event = firstStageStartedTriggeredEvent.getDomainEvent();
        var roundId = RoundId.of(event.aggregateRootId());

        var forecast = new Forecast();
        if (forecast.value().numberTimes()<3 && 6 < forecast.value().numberTimes() ){
            throw new BusinessException(forecast.value().numberTimes().toString(),"Number of repetitions needs to be greater than 3 and smaller than 6");
        }

        var round = Round.from(RoundId.of(roundId.value()), retrieveEvents());

        round.compareForecast();
        emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));





    }
}
