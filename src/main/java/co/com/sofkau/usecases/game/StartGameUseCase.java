package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.domain.game.Game;
import co.com.sofkau.domain.game.commands.StartGame;

public class StartGameUseCase extends UseCase<RequestCommand<StartGame>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<StartGame> startGameRequestCommand) {
        var command = startGameRequestCommand.getCommand();
        var game = Game.from(command.getGameId(), retrieveEvents());

        try {
            game.startGame(command.getPlayers());
            game.createRound();
            emit().onResponse(new ResponseEvents(game.getUncommittedChanges()));
        } catch (RuntimeException e) {
            emit().onError(new BusinessException(game.identity().value(), e.getMessage()));
        }
    }
}
