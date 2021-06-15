package org.slipp.masil.games.infrastructures.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("EVENTS")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EventEnvelop {


    //TODO name 이 변경되면 ?
    //TODO name 이 하는 일은 serialize 된 data 를 deserialize 하기 위한 구조를 제안한다.
    //TODO name 으로 이벤트에 대한 구독을 할 수 있다.
    private final String name;
    private final String domainEvent;
    private final String aggregateRoot;
    private final LocalDateTime occurredAt;
    //
    @Id
    private Long id;

    public static EventEnvelop of(DomainEvent aDomainEvent) {
        final String domainEventName = aDomainEvent.getClass().getName();
        final String domainEvent = Serializer.getInstance().serialize(aDomainEvent);
        final String aggregateRoot = Serializer.getInstance().serialize(aDomainEvent.getAggregateRoot());
        final LocalDateTime occurredAt = aDomainEvent.getOccurredAt();

        // TODO refactoring primitive object signetures
        return new EventEnvelop(domainEventName, domainEvent, aggregateRoot, occurredAt);
    }

    public String getDomainEventName() {
        return getName();
    }
}
