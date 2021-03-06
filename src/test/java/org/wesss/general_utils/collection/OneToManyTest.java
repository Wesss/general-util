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
        assertThat(intToStrings.size(), is(0));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));

        assertThat(intToStrings.getValues(0), empty());
        assertThat(intToStrings.getKey("zero"), nullValue());
    }

    @Test
    public void putAddsKeyValuePair() {
        intToStrings.put(0, "zero");

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(1));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));

        assertThat(intToStrings.getValues(0), contains("zero"));
        assertThat(intToStrings.getKey("zero"), is(0));

        assertThat(intToStrings.valueSet(), hasSize(1));
        assertThat(intToStrings.valueSet(), hasItems("zero"));
        assertThat(intToStrings.keySet(), hasSize(1));
        assertThat(intToStrings.keySet(), hasItems(0));
    }

    @Test
    public void multiplePutsAddsMultipleKeyValuePairs() {
        intToStrings.put(0, "zero");
        intToStrings.put(1, "one");

        assertThat(intToStrings.getValues(0), contains("zero"));
        assertThat(intToStrings.getValues(1), contains("one"));
        assertThat(intToStrings.getKey("zero"), is(0));
        assertThat(intToStrings.getKey("one"), is(1));

        assertThat(intToStrings.valueSet(), hasSize(2));
        assertThat(intToStrings.valueSet(), hasItems("zero", "one"));
        assertThat(intToStrings.keySet(), hasSize(2));
        assertThat(intToStrings.keySet(), hasItems(0, 1));
    }

    @Test
    public void keysHaveManyValues() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(2));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));
        assertThat(intToStrings.containsValue("ZERO"), is(true));

        assertThat(intToStrings.getValues(0), hasItems("zero", "ZERO"));
        assertThat(intToStrings.getKey("zero"), is(0));
        assertThat(intToStrings.getKey("ZERO"), is(0));
    }

    @Test
    public void addingValueSecondTimeRemovesFirstKeyValuePair() {
        String zero = "zero";
        intToStrings.put(0, zero);
        intToStrings.put(1, zero);

        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(1));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsKey(1), is(true));
        assertThat(intToStrings.containsValue("zero"), is(true));

        assertThat(intToStrings.getValues(1), hasItem("zero"));
        assertThat(intToStrings.getValues(0), empty());
        assertThat(intToStrings.getKey("zero"), is(1));
    }

    @Test
    public void removeNothingDoesNothing() {
        boolean removed = intToStrings.remove(0, "zero");

        assertThat(removed, is(false));
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.size(), is(0));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesKeyValuePair() {
        intToStrings.put(0, "zero");
        boolean removed = intToStrings.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.size(), is(0));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesOnlyOneKeyValuePair() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");
        boolean removed = intToStrings.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(1));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(false));
        assertThat(intToStrings.containsValue("ZERO"), is(true));
    }

    @Test
    public void removeMismatchedKeyValuePairDoesNothing() {
        intToStrings.put(0, "zero");
        intToStrings.put(1, "one");
        boolean removed = intToStrings.remove(1, "zero");

        assertThat(removed, is(false));
        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(2));
        assertThat(intToStrings.getValues(0), hasItems("zero"));
        assertThat(intToStrings.getValues(1), hasItems("one"));
    }

    @Test
    public void removeKeyRemovesManyKeyValuePairs() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");
        boolean removed = intToStrings.removeKey(0);

        assertThat(removed, is(true));
        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.size(), is(0));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
        assertThat(intToStrings.containsValue("ZERO"), is(false));
    }

    @Test
    public void removeValueRemovesKeyValuePair() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");
        boolean removed = intToStrings.removeValue("zero");

        assertThat(removed, is(true));
        assertThat(intToStrings.isEmpty(), is(false));
        assertThat(intToStrings.size(), is(1));
        assertThat(intToStrings.containsKey(0), is(true));
        assertThat(intToStrings.containsValue("zero"), is(false));
        assertThat(intToStrings.containsValue("ZERO"), is(true));
    }

    @Test
    public void clearClearsAllKeyValuePairs() {
        intToStrings.put(0, "zero");
        intToStrings.put(0, "ZERO");
        intToStrings.clear();

        assertThat(intToStrings.isEmpty(), is(true));
        assertThat(intToStrings.size(), is(0));
        assertThat(intToStrings.containsKey(0), is(false));
        assertThat(intToStrings.containsValue("zero"), is(false));
        assertThat(intToStrings.containsValue("ZERO"), is(false));
    }
}
