package io.github.dunanttonfack.generics;

import java.util.Objects;

public record Pair<A, B>(A first, B second) {

    public Pair {

        Objects.requireNonNull(first, "First must not be null");
        Objects.requireNonNull(second, "Second must not be null");
    }
}
