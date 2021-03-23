package co.com.sofkau.usecases.round;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.domain.game.values.Person;
import co.com.sofkau.domain.round.events.FirstStageStarted;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidateForCastUseCaseTest {


    @Mock
    private DomainEventRepository repository;

    @Test
    void validateForecast(){
        Person person = Person.of("hhh");
        var event =  ValidateForcastTriggeredEventWith(person);
        var useCase = new ValidateForCastUseCase();

        when(repository.getEventsBy(person.value())).thenReturn(eventStored(person));
        useCase.addRepository(repository);


    }

    private List<DomainEvent> executor (Person person, FirstStageStarted event, ValidateForCastUseCase useCase){
        return UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(person.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();
    }

    private FirstStageStarted ValidateForcastTriggeredEventWith(Person person) {
        RoundId roundId = RoundId.of("01");
        StageId stageId = StageId.of(1);
        var event = new FirstStageStarted(gameId,  stageId, players);
        event.setAggregateRootId(person.value());
        return event;
    }

    private List<DomainEvent> eventStored(Person person) {
        var stageId = StageId.of(1);
        var roundId = RoundId.of("001");
        return List.of(
                new GameStarted(playersMap),
                new RoundStarted(gameId, roundId, stageId, players),
                new GameCreated(gameId));
    }



}