package org.slipp.masil.games.domains;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;

@Value(staticConstructor = "of")
public class Target {

    @Column(value = "TARGET")
    Long value;

    public Target(Long value) {
        if (value < 0) {
            throw new IllegalStateException("target is invalid");
        }
        this.value = value;
    }
}
