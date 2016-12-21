package org.wesss.general_utils.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OneToManyTest {

    private OneToMany<Integer, String> intToStrings;

    public OneToManyTest() {
        intToStrings = new OneToMany<>();
    }

    @Test
    public void newOneToManyHasNothing() {
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));

        assertThat(intToStrings.get(0), empty());
        assertThat(intToStrings.getKey("zero"), nullValue());
    }

    @Test
    public void putAddsKeyValuePair() {
        intToStrings.put(0, "zero");

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));

        assertThat(intToStrings.get(0), contains("zero"));
        assertThat(intToStrings.getKey("zero"), is(0));
    }

    @Test
    public void keysHaveManyValues() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));
        assertThat(intToStrings.containsValue("ZERO"), is(true));

        assertThat(intToStrings.get(0), hasItems("zero", "ZERO"));
        assertThat(intToStrings.getKey("zero"), is(0));
        assertThat(intToStrings.getKey("ZERO"), is(0));
    }

    @Test
    public void addingValueSecondTimeRemovesFirstKeyValuePair() {
        String zero = "zero";
        intToStrings.put(0, zero);
        intToStrings.put(1, zero);

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsKey(1), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));

        assertThat(intToStrings.get(1), hasItem("zero"));
        assertThat(intToStrings.get(0), empty());
        assertThat(intToStrings.getKey("zero"), is(1));
    }

    @Test
    public void removeNothingDoesNothing() {
        boolean removed = intToStrings.remove(0, "zero");

        assertThat(removed, is(false));
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesKeyValuePair() {
        intToStrings.put(0, "zero");
        boolean removed = intToStrings.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void clearClearsAllKeyValuePairs() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");
        intToStrings.clear();

        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
        assertThat(intToStrings.containsValue("ZERO"), is(false));
    }
}
