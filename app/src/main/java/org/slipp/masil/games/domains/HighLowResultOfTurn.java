package org.slipp.masil.games.domains;

public class HighLowResultOfTurn {
    public static final HighLowResultOfTurn NONE = new HighLowResultOfTurn();
    public static final HighLowResultOfTurn MATCHED = new HighLowResultOfTurn();
    public static final HighLowResultOfTurn LOW = new HighLowResultOfTurn();
    public static final HighLowResultOfTurn HIGH = new HighLowResultOfTurn();

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
