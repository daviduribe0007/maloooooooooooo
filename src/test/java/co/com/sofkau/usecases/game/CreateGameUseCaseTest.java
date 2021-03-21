package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofkau.domain.game.commands.CreateGame;
import co.com.sofkau.domain.game.events.GameCreated;
import co.com.sofkau.domain.game.events.PlayerCreated;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

class CreateGameUseCaseTest {

    @Test
    void createGame(){
        var names = Map.of(Person.of("1018"), new Name("Kev"),
                Person.of("350"), new Name("Wil"));
        var cashes = Map.of(Person.of("1018"), new Cash(1000F),
                Person.of("350"), new Cash(1000F));
        var command = new CreateGame(names, cashes);
        var useCase = new CreateGameUseCase();

        var event = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var gameCreated = (GameCreated) event.get(0);
        var player = (PlayerCreated) event.get(1);

        Assertions.assertTrue(Objects.nonNull(gameCreated.getGameId().value()));

        Assertions.assertEquals("1018", player.getPerson().value());
        Assertions.assertEquals("Kev", player.getName().value());
        Assertions.assertEquals(1000, player.getCash().value());

    }

    @Test
    void ErrorCreatingGame() {
        var names = Map.of(Person.of("1018"), new Name("Kev"));
        var cashes = Map.of(Person.of("1018"), new Cash(1000F));
        var command = new CreateGame(names, cashes);
        var useCase = new CreateGameUseCase();

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        }, "Can't create game, need two or more players to create game");

    }

}