package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.Player;
import co.com.sofkau.domain.game.commands.StartGame;
import co.com.sofkau.domain.game.events.GameCreated;
import co.com.sofkau.domain.game.events.GameStarted;
import co.com.sofkau.domain.game.events.PlayerCreated;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartGameUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void startGame() {
        var id = GameId.of("001");
        var players = Map.of(
                Person.of("1018"), new Player(Person.of("1018"), new Name("Kev"), new Cash(1000F)),
                Person.of("350"), new Player(Person.of("350"), new Name("Wil"), new Cash(1000F)),
                Person.of("636"), new Player(Person.of("636"), new Name("Ale"), new Cash(1000F)));

        var command = new StartGame(id, players);
        var useCase = new StartGameUseCase();

        when(repository.getEventsBy(id.value())).thenReturn(eventStored(id));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(id.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var gameStarted = (GameStarted) events.get(0);
        Assertions.assertEquals(3, gameStarted.getPlayers().size());

    }

    private List<DomainEvent> eventStored(GameId id) {
        return List.of(
                new GameCreated(id),
                new PlayerCreated(Person.of("1018"), new Name("Kev"), new Cash(1000F)),
                new PlayerCreated(Person.of("350"), new Name("Wil"), new Cash(1000F)),
                new PlayerCreated(Person.of("636"), new Name("Ale"), new Cash(1000F))
        );
    }

}