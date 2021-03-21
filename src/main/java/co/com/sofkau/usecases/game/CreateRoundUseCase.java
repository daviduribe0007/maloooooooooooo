package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.events.GameStarted;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.Round;

public class CreateRoundUseCase extends UseCase<TriggeredEvent<GameStarted>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<GameStarted> gameStartedTriggeredEvent) {

        var event = gameStartedTriggeredEvent.getDomainEvent();
        var roundId = new RoundId();

        if(event.getPlayers().size() < 2) {
            throw new BusinessException(roundId.value(), "Can't create round. Game need assign winner");
        }

        var gameId = GameId.of(event.aggregateRootId());
        var round = new Round(roundId, gameId, event.getPlayers().keySet());

        emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));

    }
}
