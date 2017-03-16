package org.wesss.general_utils.math;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.wesss.general_utils.math.MathUtils.gcd;
import static org.wesss.general_utils.math.MathUtils.hypotenuse;
import static org.wesss.general_utils.math.MathUtils.modPos;

public class MathUtilsTest {

    @Test
    public void gcdOnNumbersReturnsGcd() {
        assertThat(gcd(12, 18), is(6));
    }

    @Test
    public void gcdOnNegativesReturnsPositiveGcd() {
        assertThat(gcd(-12, 18), is(6));
        assertThat(gcd(12, -18), is(6));
        assertThat(gcd(-12, -18), is(6));
    }

    @Test
    public void gcdOnPrimesReturnsOne() {
        assertThat(gcd(23, 17), is(1));
    }

    @Test
    public void gcdOnZeroReturnsOtherNumber() {
        assertThat(gcd(23, 0), is(23));
        assertThat(gcd(0, 17), is(17));
    }

    @Test
    public void gcdOnOneReturnsOne() {
        assertThat(gcd(23, 1), is(1));
        assertThat(gcd(1, 17), is(1));
    }

    @Test
    public void gcdOnZerosReturnsNaN() {
        // TODO more gcd tests
//        assertThat(gcd(0, 0), is(1));
    }

    @Test
    public void hypotenuseReturnsHypotenuse() {
        assertThat(hypotenuse(3, 4), is(5));
    }

    @Test
    public void hypotenuseOnNegativesReturnsPositiveHypotenuse() {
        assertThat(hypotenuse(-3, 4), is(5));
        assertThat(hypotenuse(3, -4), is(5));
        assertThat(hypotenuse(-3, -4), is(5));
    }

    @Test
    public void hypotenuseTruncatesDownToNearestInt() {
        assertThat(hypotenuse(4, 5), is(6));
    }

    @Test
    public void hypotenuseOnZeroReturnsOtherLength() {
        assertThat(hypotenuse(0, 5), is(5));
    }

    @Test
    public void hypotenuseOnZerosReturnsZero() {
        assertThat(hypotenuse(0, 0), is(0));
    }

    @Test
    public void modPosGivesModulus() {
        assertThat(modPos(5, 3), is(2));
    }

    @Test
    public void modPosOnNegativeGivesPositiveModulus() {
        assertThat(modPos(-5, 3), is(1));
    }

    @Test
    public void modPosOnNegativeModGivesModulus() {
        assertThat(modPos(5, -3), is(2));
    }

    @Test
    public void getEnumerationsSmall() {
        getEnumerationsTest(5);
    }

    @Test(timeout = 60000)
    public void getEnumerationsLarge() {
        getEnumerationsTest(14);
    }

    private static void getEnumerationsTest(int size) {
        Set<Object> enumeratedSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            enumeratedSet.add(new Object());
        }
        Iterable<Set<Object>> enumerations = MathUtils.getEnumerations(enumeratedSet);

        Set<Set<Object>> seenEnumerations = new HashSet<>();
        int expectedEnumerationsSize = (int) Math.pow(2, size);

        int prevEnumerationSize = -1;
        int enumerationNo = 0;
        int prevPercentComplete = 0;
        for (Set<Object> enumeration : enumerations) {
            // print progress
            int percentComplete = (enumerationNo) * 100 / expectedEnumerationsSize;
            if (percentComplete > prevPercentComplete) {
                System.out.println("EnumerationTest " + percentComplete + "% finished");
            }
            prevPercentComplete = percentComplete;
            enumerationNo++;

            // assert this is a new enumeration
            assertThat(seenEnumerations, not(hasItem(enumeration)));
            // that is purely an enumeration of the given set
            assertThat(enumeratedSet, hasItems(enumeration.toArray()));
            // given in the correct order
            assertThat(enumeration, hasSize(greaterThanOrEqualTo(prevEnumerationSize)));

            seenEnumerations.add(enumeration);
            prevEnumerationSize = enumeration.size();
        }

        // assert we have seen every possible enumeration
        assertThat(seenEnumerations, hasSize(expectedEnumerationsSize));
    }
}
