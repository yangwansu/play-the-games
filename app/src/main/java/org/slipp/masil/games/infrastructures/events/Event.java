package org.slipp.masil.games.infrastructures.events;

import org.springframework.data.annotation.Id;

public class Event {
    @Id
    private Long id;
    //TODO type 이 변경되면 ?
    //TODO type 이 하는 일은 serialize 된 data 를 deserialize 하기 위한 구조를 제안한다.
    //TODO type 으로 이벤트에 대한 구독을 할 수 있다.
    private String type;

    private EventObject object;
}
