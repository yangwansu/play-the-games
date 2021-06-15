package org.slipp.masil.games.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

public class RandomUtil {

    private RandomUtil() {
        throw new IllegalArgumentException();
    }

    public static Long generateNumbers(final int startInclusive, final int endInclusive) {
        List<Long> numbers = new ArrayList<>();
        LongStream.range(startInclusive, endInclusive)
                .forEach(numbers::add);

        Collections.shuffle(numbers);
        return numbers.get(0);
    }
}
