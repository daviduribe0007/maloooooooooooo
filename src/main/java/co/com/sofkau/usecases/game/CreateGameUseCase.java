package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofkau.domain.game.Game;
import co.com.sofkau.domain.game.commands.CreateGame;
import co.com.sofkau.domain.game.factory.PlayerFactory;
import co.com.sofkau.domain.game.values.GameId;

public class CreateGameUseCase extends UseCase<RequestCommand<CreateGame>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateGame> input) {
        var command = input.getCommand();
        var gameId = new GameId();
        var factory = PlayerFactory.builder();

        command.getNames()
                .forEach((personId, name) ->
                        factory.newPlayer(personId, name, command.getCashes().get(personId)));

        if (factory.players().size() < 2) {
            throw new BusinessException(gameId.value(), "Game can't be created, need two or more players");
        }

        var game = new Game(gameId, factory);

        emit().onResponse(new ResponseEvents(game.getUncommittedChanges()));

    }
}
