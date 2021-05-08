package org.slipp.masil.games.domains;

import lombok.Value;

@Value(staticConstructor = "of")
public class HighLowTurn {
    int guess;

}
