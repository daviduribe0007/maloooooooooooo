package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.domain.round.Round;
import co.com.sofkau.domain.round.commands.StartRound;

public class StartRoundUseCase extends UseCase<RequestCommand<StartRound>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<StartRound> startRoundRequestCommand) {
        var command = startRoundRequestCommand.getCommand();
        var round = Round.from(command.roundId(), retrieveEvents());

        try {
            round.startRound(command.gameId(), command.roundId(), command.players());
            round.rollDices();
            emit().onResponse(new ResponseEvents(round.getUncommittedChanges()));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(round.identity().value(), e.getMessage()));
        }

    }
}
