package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.events.RoundCreated;
import co.com.sofkau.domain.game.values.*;
import co.com.sofkau.domain.round.commands.StartRound;
import co.com.sofkau.domain.round.events.RoundStarted;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;


import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StartRoundUseCaseTest {

    private final Set<Person> players = Set.of(
            Person.of("1018"), Person.of("636")
    );

    private final GameId gameId = GameId.of("0001");

    @Mock
    private DomainEventRepository repository;

    @Test
    void StartRound() {
        var roundId = RoundId.of("01");
        var command = new StartRound(gameId, roundId, players);
        var useCase = new StartRoundUseCase();

        when(repository.getEventsBy(roundId.value())).thenReturn(eventStored(roundId));
        useCase.addRepository(repository);

        var events = executor(roundId, command, useCase);
        var roundStarted = (RoundStarted) events.get(0);

        Assertions.assertEquals(roundId, roundStarted.getRoundId());
        Assertions.assertEquals(gameId, roundStarted.getGameId());
    }

    private List<DomainEvent> executor (RoundId roundId, StartRound command, StartRoundUseCase useCase){
        return UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(roundId.toString())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();
    }

    private List<DomainEvent> eventStored(RoundId id) {
        return List.of(
                new RoundCreated(gameId, id, players)
        );
    }

}