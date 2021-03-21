package co.com.sofkau.usecases.game;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofkau.domain.game.Player;
import co.com.sofkau.domain.game.events.GameStarted;
import co.com.sofkau.domain.game.events.RoundCreated;
import co.com.sofkau.domain.game.values.Cash;
import co.com.sofkau.domain.game.values.Name;
import co.com.sofkau.domain.game.values.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;


class CreateRoundUseCaseTest {

    @Test
    void createRound(){
        var event = new GameStarted(Map.of(
                Person.of("1018"), new Player(Person.of("1018"), new Name("Kevn"), new Cash(1000F)),
                Person.of("350"), new Player(Person.of("350"), new Name("Wil"), new Cash(1000F)),
                Person.of("636"), new Player(Person.of("636"), new Name("Ale"), new Cash(1000F))
                ));
        event.setAggregateRootId("0001");
        var useCase = new CreateRoundUseCase();

        var events = UseCaseHandler
                .getInstance()
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var roundCreated = (RoundCreated) events.get(0);

        Assertions.assertEquals("0001", roundCreated.getGameId().value());
        Assertions.assertEquals(3, roundCreated.getPlayers().size());
    }

    @Test
    void createRoundError() {
        var event = new GameStarted(Map.of(
                Person.of("1018"), new Player(Person.of("1018"), new Name("Kevn"), new Cash(1000F))
        ));
        event.setAggregateRootId("0001");
        var useCase = new CreateRoundUseCase();

        Assertions.assertThrows(BusinessException.class, () -> UseCaseHandler
        .getInstance()
        .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow(), "Can't create round. Game need assign winner");

    }
}