package org.slipp.masil.games.domains;

import lombok.Value;

@Value(staticConstructor = "of")
public class Score {
    int value;

    public boolean isValid() {
        return value >= 0;
    }

    public Score plus() {
        return Score.of(value + 1);
    }
}
