package org.slipp.masil.games.domains;

import org.slipp.masil.games.domains.highrow.HighLowJudgement;

public interface Judge {
    HighLowJudgement judge(Long guessNumber);

    Target getTarget();
}
