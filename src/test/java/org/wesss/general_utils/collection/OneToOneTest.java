package org.wesss.general_utils.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OneToOneTest {

    private OneToOne<Integer, String> intToString;

    public OneToOneTest() {
        intToString = new OneToOne<>();
    }

    @Test
    public void newOneToOneHasNothing() {
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue(""), is(false));

        assertThat(intToString.getValue(0), nullValue());
        assertThat(intToString.getKey("zero"), nullValue());
    }

    @Test
    public void putAddsKeyValuePair() {
        intToString.put(0, "zero");

        assertThat(intToString.isEmpty(), is(false));
        assertThat(intToString.size(), is(1));
        assertThat(intToString.containsKey(0), is(true));
        assertThat(intToString.containsValue("zero"), is(true));

        assertThat(intToString.getValue(0), is("zero"));
        assertThat(intToString.getKey("zero"), is(0));

        assertThat(intToString.valueSet(), hasSize(1));
        assertThat(intToString.valueSet(), hasItems("zero"));
        assertThat(intToString.keySet(), hasSize(1));
        assertThat(intToString.keySet(), hasItems(0));
    }

    @Test
    public void multiplePutsAddsMultipleKeyValuePairs() {
        intToString.put(0, "zero");
        intToString.put(1, "one");

        assertThat(intToString.getValue(0), is("zero"));
        assertThat(intToString.getValue(1), is("one"));
        assertThat(intToString.getKey("zero"), is(0));
        assertThat(intToString.getKey("one"), is(1));

        assertThat(intToString.valueSet(), hasSize(2));
        assertThat(intToString.valueSet(), hasItems("zero", "one"));
        assertThat(intToString.keySet(), hasSize(2));
        assertThat(intToString.keySet(), hasItems(0, 1));
    }

    @Test
    public void addingKeySecondTimeRemovesFirstKeyValuePair() {
        intToString.put(0, "zero");
        intToString.put(0, "ZERO");

        assertThat(intToString.isEmpty(), is(false));
        assertThat(intToString.size(), is(1));
        assertThat(intToString.containsKey(0), is(true));
        assertThat(intToString.containsValue("zero"), is(false));
        assertThat(intToString.containsValue("ZERO"), is(true));

        assertThat(intToString.getValue(0), is("ZERO"));
        assertThat(intToString.getKey("zero"), nullValue());
        assertThat(intToString.getKey("ZERO"), is(0));
    }

    @Test
    public void addingValueSecondTimeRemovesFirstKeyValuePair() {
        String zero = "zero";
        intToString.put(0, zero);
        intToString.put(1, zero);

        assertThat(intToString.isEmpty(), is(false));
        assertThat(intToString.size(), is(1));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsKey(1), is(true));
        assertThat(intToString.containsValue("zero"), is(true));

        assertThat(intToString.getValue(1), is("zero"));
        assertThat(intToString.getValue(0), nullValue());
        assertThat(intToString.getKey("zero"), is(1));
    }

    @Test
    public void removeNothingDoesNothing() {
        boolean removed = intToString.remove(0, "zero");

        assertThat(removed, is(false));
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesKeyValuePair() {
        intToString.put(0, "zero");
        boolean removed = intToString.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue("zero"), is(false));
    }

    @Test
    public void removeMismatchedKeyValuePairDoesNothing() {
        intToString.put(0, "zero");
        intToString.put(1, "one");
        boolean removed = intToString.remove(1, "zero");

        assertThat(removed, is(false));
        assertThat(intToString.isEmpty(), is(false));
        assertThat(intToString.size(), is(2));
        assertThat(intToString.getValue(0), is("zero"));
        assertThat(intToString.getValue(1), is("one"));
    }

    @Test
    public void removeKeyRemovesKeyValuePair() {
        intToString.put(0, "zero");
        boolean removed = intToString.removeKey(0);

        assertThat(removed, is(true));
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue("zero"), is(false));
    }

    @Test
    public void removeValueRemovesKeyValuePair() {
        intToString.put(0, "zero");
        boolean removed = intToString.removeValue("zero");

        assertThat(removed, is(true));
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue("zero"), is(false));
    }

    @Test
    public void clearClearsAllKeyValuePairs() {
        intToString.put(0, "zero");
        intToString.put(0, "ZERO");
        intToString.clear();

        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.size(), is(0));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue("zero"), is(false));
        assertThat(intToString.containsValue("ZERO"), is(false));
    }
}
