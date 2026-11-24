package io.github.dunanttonfack.generics;

import java.util.List;

public class GenericUtils {

    public static <T extends Comparable<T>> T max(List<T> list) {

        if (list.isEmpty()) {
            throw new IllegalArgumentException("list must not be empty");
        }
        T max = list.get(0);
        for (T t : list) {
            if (t.compareTo(max) > 0) {
                max = t;
            }
        }
        return max;
    }
}
