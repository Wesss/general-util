package org.util.test;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaMatcher<T> extends BaseMatcher<T> {

    // TODO have error message change to show uncaught exceptions in the matches method

    Predicate<T> matchingPredicate;
    Consumer<Description>      expectedDescriptor;
    BiConsumer<T, Description> mismatchDescriptor;

    public LambdaMatcher(Predicate<T> matchingPredicate,
                         Consumer<Description> expectedDescriptor,
                         BiConsumer<T, Description> mismatchDescriptor) {
        this.matchingPredicate = matchingPredicate;
        this.expectedDescriptor = expectedDescriptor;
        this.mismatchDescriptor = mismatchDescriptor;
    }

    @Override
    public boolean matches(Object item) {
        try {
            return matchingPredicate.test((T) item);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        expectedDescriptor.accept(description);
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        T typedItem;
        try {
            typedItem = (T) item;
        } catch (Exception e) {
            description.appendText("item could not be cast to inferred type");
            return;
        }

        try {
            mismatchDescriptor.accept(typedItem, description);
        } catch (Exception e) {
            description.appendText("mismatch descriptor threw exception: " + e);
        }
    }
}
