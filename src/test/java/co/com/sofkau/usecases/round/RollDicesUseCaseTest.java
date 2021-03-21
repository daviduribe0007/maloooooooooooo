package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.events.RoundCreated;
import co.com.sofkau.domain.game.values.GameId;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.game.values.RoundId;
import co.com.sofkau.domain.round.events.RolledDice;
import co.com.sofkau.domain.round.events.RoundStarted;
import co.com.sofkau.domain.round.values.StageId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RollDicesUseCaseTest {
    
    private final Set<Person> players = Set.of(
            Person.of("1018"), Person.of("636")
    );
    private final GameId gameId = GameId.of("0001");

    private final StageId stageId = StageId.of(1);


    @Mock
    private DomainEventRepository repository;

    @Test
    void RollDices(){
        var roundId = RoundId.of("01");
        var event = createTriggeredEventWith(roundId);

        var useCase = new RollDicesUseCase();
        when(repository.getEventsBy(roundId.value())).thenReturn(eventStored(roundId));
        useCase.addRepository(repository);

        var events = executor(roundId, event, useCase);
        var rolledDice = (RolledDice) events.get(0);

        Assertions.assertEquals(gameId, rolledDice.gameId());
        Assertions.assertEquals(6, rolledDice.diceValues().size());


    }

    private RoundStarted createTriggeredEventWith(RoundId roundId){
        var event = new RoundStarted(gameId, roundId, stageId, players);
        event.setAggregateRootId(roundId.value());
        return event;
    }

    private List<DomainEvent> executor (RoundId roundId, RoundStarted event, RollDicesUseCase useCase){
        return UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(roundId.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();
    }

    private List<DomainEvent> eventStored(RoundId roundId) {
        return List.of(
                new RoundCreated(gameId, roundId, players),
                new RoundStarted(gameId, roundId, stageId, players)
        );
    }
}