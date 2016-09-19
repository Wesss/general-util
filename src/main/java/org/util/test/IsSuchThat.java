package org.util.test;

import org.hamcrest.Matcher;

import java.util.function.Function;
import java.util.function.Predicate;

import static org.mockito.Matchers.argThat;

public class IsSuchThat {

    // TODO IsSuchThat documentation

    private static final String DEFAULT_EXPECTED_BEHAVIOR_DESCRIPTION = "a value matching given predicate";
    private static final String DEFAULT_FAILED_DESCRIPTION = "did not match";

    /**
     * @param matchingPredicate
     * @param <V>
     * @return
     */
    public static <V> Matcher<V> isSuchThat(Predicate<V> matchingPredicate) {
        return new LambdaMatcher<>(matchingPredicate,
                desc -> desc.appendText(DEFAULT_EXPECTED_BEHAVIOR_DESCRIPTION),
                (mismatch, desc) -> desc.appendText(DEFAULT_FAILED_DESCRIPTION));
    }

    /**
     * @param matchingPredicate
     * @param expectedBehavior
     * @param <V>
     * @return
     */
    public static <V> Matcher<V> isSuchThat(Predicate<V> matchingPredicate,
                                            String expectedBehavior) {
        return new LambdaMatcher<>(matchingPredicate,
                desc -> desc.appendText(expectedBehavior),
                (mismatch, desc) -> desc.appendText(DEFAULT_FAILED_DESCRIPTION));
    }

    /**
     * @param matchingPredicate
     * @param expectedBehavior
     * @param failureDescription
     * @param <V>
     * @return
     */
    public static <V> Matcher<V> isSuchThat(Predicate<V> matchingPredicate,
                                            String expectedBehavior,
                                            String failureDescription) {
        return new LambdaMatcher<>(matchingPredicate,
                desc -> desc.appendText(expectedBehavior),
                (mismatch, desc) -> desc.appendText(failureDescription));
    }

    /**
     * @param matchingPredicate
     * @param expectedBehavior
     * @param failureDescriptionFunction
     * @param <V>
     * @return
     */
    public static <V> Matcher<V> isSuchThat(Predicate<V> matchingPredicate,
                                            String expectedBehavior,
                                            Function<V, String> failureDescriptionFunction) {
        return new LambdaMatcher<>(matchingPredicate,
                desc -> desc.appendText(expectedBehavior),
                (mismatch, desc) -> desc.appendText(failureDescriptionFunction.apply(mismatch)));
    }

    /**
     * For mockito argument matchers
     *
     * @param matchingPredicate
     * @param <V>
     * @return
     */
    public static <V> V argSuchThat(Predicate<V> matchingPredicate) {
        return argThat(isSuchThat(matchingPredicate));
    }
}
