package org.wesss.general_utils.collection;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionFactory {
    @SafeVarargs
    public static <T> ArrayList<T> ArrayListOf(T... elements) {
        ArrayList<T> result = new ArrayList<>();
        Collections.addAll(result, elements);
        return result;
    }
}
