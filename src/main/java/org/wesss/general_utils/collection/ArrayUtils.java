package org.wesss.general_utils.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayUtils {

    @SafeVarargs
    public static <T> Set<T> asSet(T... args) {
        return new HashSet<>(Arrays.asList(args));
    }

    @SafeVarargs
    public static <T> List<T> asList(T... args) {
        return Arrays.asList(args);
    }
}
