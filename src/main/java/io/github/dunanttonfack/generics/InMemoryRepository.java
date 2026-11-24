package io.github.dunanttonfack.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class InMemoryRepository<T> {

    private final List<T> items = new ArrayList<>();

    public void save(T item) {
        Objects.requireNonNull(item, "item must not be null");
        items.add(item);
    }

    public List<T> findAll() {

        return List.copyOf(items);
    }

    public Optional<T> findFirst(Predicate<? super T> condition) {

        return items.stream()
                .filter(condition)
                .findFirst();
    }

    public long count() {

        return items.size();
    }
}
