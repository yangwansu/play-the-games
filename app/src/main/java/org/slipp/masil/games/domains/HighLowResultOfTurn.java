package org.slipp.masil.games.domains;

public enum HighLowResultOfTurn {
    NONE, MATCHED, LOW, HIGH;

    public static HighLowResultOfTurn isLow() {
        return LOW;
    }

    public static HighLowResultOfTurn isHigh() {
        return HIGH;
    }

    public static HighLowResultOfTurn isMatched() {
        return MATCHED;
    }
}
