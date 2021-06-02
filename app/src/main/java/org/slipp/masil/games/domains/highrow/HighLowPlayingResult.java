package org.slipp.masil.games.domains.highrow;

import lombok.Value;

@Value
public class HighLowPlayingResult {
    Long contextId;
    HighLowJudgement judgement;
}
