package org.wesss.general_utils.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ManyToManyTest {

    private ManyToMany<Integer, String> intsToStrings;

    public ManyToManyTest() {
        intsToStrings = new ManyToMany<>();
    }

    @Test
    public void newOneToManyHasNothing() {
        assertThat(intsToStrings.isEmpty(), is(true));
        assertThat(intsToStrings.size(), is(0));
        assertThat(intsToStrings.containsKey(0), is(false));
        assertThat(intsToStrings.containsValue("zero"), is(false));

        assertThat(intsToStrings.getValues(0), empty());
        assertThat(intsToStrings.getKeys("zero"), empty());
    }

    @Test
    public void putAddsKeyValuePair() {
        intsToStrings.put(0, "zero");

        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(1));
        assertThat(intsToStrings.containsKey(0), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(true));

        assertThat(intsToStrings.getValues(0), contains("zero"));
        assertThat(intsToStrings.getKeys("zero"), contains(0));

        assertThat(intsToStrings.valueSet(), hasSize(1));
        assertThat(intsToStrings.valueSet(), hasItems("zero"));
        assertThat(intsToStrings.keySet(), hasSize(1));
        assertThat(intsToStrings.keySet(), hasItems(0));
    }

    @Test
    public void multiplePutsAddsMultipleKeyValuePairs() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(1, "one");

        assertThat(intsToStrings.getValues(0), contains("zero"));
        assertThat(intsToStrings.getValues(1), contains("one"));
        assertThat(intsToStrings.getKeys("zero"), contains(0));
        assertThat(intsToStrings.getKeys("one"), contains(1));

        assertThat(intsToStrings.valueSet(), hasSize(2));
        assertThat(intsToStrings.valueSet(), hasItems("zero", "one"));
        assertThat(intsToStrings.keySet(), hasSize(2));
        assertThat(intsToStrings.keySet(), hasItems(0, 1));
    }

    @Test
    public void keysHaveManyValues() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(0, "ZERO");

        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(2));
        assertThat(intsToStrings.containsKey(0), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(true));
        assertThat(intsToStrings.containsValue("ZERO"), is(true));

        assertThat(intsToStrings.getValues(0), hasItems("zero", "ZERO"));
        assertThat(intsToStrings.getKeys("zero"), contains(0));
        assertThat(intsToStrings.getKeys("ZERO"), contains(0));
    }

    @Test
    public void valuesHaveManyKeys() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(10, "zero");

        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(2));
        assertThat(intsToStrings.containsKey(0), is(true));
        assertThat(intsToStrings.containsKey(10), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(true));

        assertThat(intsToStrings.getValues(0), hasItems("zero"));
        assertThat(intsToStrings.getKeys("zero"), hasItems(0, 10));
    }

    @Test
    public void removeNothingDoesNothing() {
        boolean removed = intsToStrings.remove(0, "zero");

        assertThat(removed, is(false));
        assertThat(intsToStrings.isEmpty(), is(true));
        assertThat(intsToStrings.size(), is(0));
        assertThat(intsToStrings.containsKey(0), is(false));
        assertThat(intsToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesKeyValuePair() {
        intsToStrings.put(0, "zero");
        boolean removed = intsToStrings.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intsToStrings.isEmpty(), is(true));
        assertThat(intsToStrings.size(), is(0));
        assertThat(intsToStrings.containsKey(0), is(false));
        assertThat(intsToStrings.containsValue("zero"), is(false));
    }

    @Test
    public void removeRemovesOnlyOneKeyValuePair() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(0, "ZERO");
        intsToStrings.put(10, "zero");
        intsToStrings.put(10, "ZERO");
        boolean removed = intsToStrings.remove(0, "zero");

        assertThat(removed, is(true));
        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(3));

        assertThat(intsToStrings.containsKey(0), is(true));
        assertThat(intsToStrings.containsKey(10), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(true));
        assertThat(intsToStrings.containsValue("ZERO"), is(true));

        assertThat(intsToStrings.getValues(0), hasSize(1));
        assertThat(intsToStrings.getValues(0), hasItems("ZERO"));
        assertThat(intsToStrings.getValues(10), hasSize(2));
        assertThat(intsToStrings.getValues(10), hasItems("zero", "ZERO"));
        assertThat(intsToStrings.getKeys("zero"), hasSize(1));
        assertThat(intsToStrings.getKeys("zero"), hasItems(10));
        assertThat(intsToStrings.getKeys("ZERO"), hasSize(2));
        assertThat(intsToStrings.getKeys("ZERO"), hasItems(0, 10));
    }

    @Test
    public void removeKeyRemovesManyKeyValuePairs() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(0, "ZERO");
        intsToStrings.put(10, "zero");
        intsToStrings.put(10, "ZERO");
        boolean removed = intsToStrings.removeKey(0);

        assertThat(removed, is(true));
        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(2));

        assertThat(intsToStrings.containsKey(0), is(false));
        assertThat(intsToStrings.containsKey(10), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(true));
        assertThat(intsToStrings.containsValue("ZERO"), is(true));

        assertThat(intsToStrings.getValues(0), hasSize(0));
        assertThat(intsToStrings.getValues(10), hasSize(2));
        assertThat(intsToStrings.getValues(10), hasItems("zero", "ZERO"));
        assertThat(intsToStrings.getKeys("zero"), hasSize(1));
        assertThat(intsToStrings.getKeys("zero"), hasItems(10));
        assertThat(intsToStrings.getKeys("ZERO"), hasSize(1));
        assertThat(intsToStrings.getKeys("ZERO"), hasItems(10));
    }

    @Test
    public void removeValueRemovesManyKeyValuePairs() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(0, "ZERO");
        intsToStrings.put(10, "zero");
        intsToStrings.put(10, "ZERO");
        boolean removed = intsToStrings.removeValue("zero");

        assertThat(removed, is(true));
        assertThat(intsToStrings.isEmpty(), is(false));
        assertThat(intsToStrings.size(), is(2));

        assertThat(intsToStrings.containsKey(0), is(true));
        assertThat(intsToStrings.containsKey(10), is(true));
        assertThat(intsToStrings.containsValue("zero"), is(false));
        assertThat(intsToStrings.containsValue("ZERO"), is(true));

        assertThat(intsToStrings.getValues(0), hasSize(1));
        assertThat(intsToStrings.getValues(10), hasItems("ZERO"));
        assertThat(intsToStrings.getValues(10), hasSize(1));
        assertThat(intsToStrings.getValues(10), hasItems("ZERO"));
        assertThat(intsToStrings.getKeys("zero"), hasSize(0));
        assertThat(intsToStrings.getKeys("ZERO"), hasSize(2));
        assertThat(intsToStrings.getKeys("ZERO"), hasItems(0, 10));
    }

    @Test
    public void clearClearsAllKeyValuePairs() {
        intsToStrings.put(0, "zero");
        intsToStrings.put(0, "ZERO");
        intsToStrings.put(10, "zero");
        intsToStrings.put(10, "ZERO");
        intsToStrings.clear();

        assertThat(intsToStrings.isEmpty(), is(true));
        assertThat(intsToStrings.size(), is(0));
        assertThat(intsToStrings.containsKey(0), is(false));
        assertThat(intsToStrings.containsKey(10), is(false));
        assertThat(intsToStrings.containsValue("zero"), is(false));
        assertThat(intsToStrings.containsValue("ZERO"), is(false));
    }
}
