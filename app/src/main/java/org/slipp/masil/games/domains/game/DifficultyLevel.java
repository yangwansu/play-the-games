package org.slipp.masil.games.domains.game;

import org.slipp.masil.games.domains.Target;

import java.util.function.Supplier;

public enum DifficultyLevel {
    //    RandomUtil.generateNumbers(1, 9)
    EASY(() -> 1L);

    private final Supplier<Long> generator;

    DifficultyLevel(Supplier<Long> generator) {
        this.generator = generator;
    }

    public Target create() {
        return Target.of(generator.get());
    }
}
