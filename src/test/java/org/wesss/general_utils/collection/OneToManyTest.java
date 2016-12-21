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

    // TODO OneToMany remove/clear tests
}
