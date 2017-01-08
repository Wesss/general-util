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
        assertThat(intToString.size(), is(0));
        assertThat(intToString.isEmpty(), is(true));
        assertThat(intToString.containsKey(0), is(false));
        assertThat(intToString.containsValue(""), is(false));
    }
}
