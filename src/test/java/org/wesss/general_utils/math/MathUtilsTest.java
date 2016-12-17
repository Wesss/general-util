package org.wesss.general_utils.math;

import org.junit.Test;

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
}
