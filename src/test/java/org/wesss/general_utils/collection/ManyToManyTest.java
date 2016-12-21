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
    public void newManyToManyHasNothing() {
        //TODO test ManyToMany
    }
}
