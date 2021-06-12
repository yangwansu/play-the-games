package org.slipp.masil.games.infrastructures.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slipp.masil.games.domains.highrow.StartHighLowPlay;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContext;
import org.slipp.masil.games.domains.highrow.HighLowPlayingContextFactory;
import org.slipp.masil.games.domains.highrow.HighLowPlayStarted;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EventEnvelopCreateTest {

    HighLowPlayingContext context;

    @BeforeEach
    void setUp() {
        context = HighLowPlayingContextFactory.DEFAULT.create(new StartHighLowPlay("Foo"));
    }

    @Test
    void build() {

        HighLowPlayStarted domainEvent = new HighLowPlayStarted(context);
        EventEnvelop envelop = EventEnvelop.of(domainEvent);

        assertThat(envelop.getId()).isNull(); //TODO fill
        //assertThat(envelop.getVersion).isNull();
        assertThat(envelop.getOccurredAt()).isEqualTo(domainEvent.getOccurredAt());


        assertThat(envelop.getDomainEventName()).isEqualTo(HighLowPlayStarted.class.getName());
        assertThat(envelop.getAggregateRoot()).isEqualTo(Serializer.getInstance().serialize(context));

        HighLowPlayStarted deserialize = Serializer.getInstance().deserialize(envelop.getDomainEvent(), HighLowPlayStarted.class);

        assertThat(envelop.getDomainEvent())
                .isEqualTo(Serializer.getInstance().serialize(deserialize));
        //assertThat(envelop.getProperties("name")).isEqualTo("");

    }
}
