package org.util.test;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.util.test.IsSuchThat.argSuchThat;
import static org.util.test.IsSuchThat.isSuchThat;

public class LambdaMatcherTest {

    public ObjGet ObjGet = mock(ObjGet.class);

    @Before
    public void before() {
        reset(ObjGet);
    }

    @Test
    public void objMatchesObj() {
        Object obj = new Object();

        assertThat(obj, isSuchThat(o -> o.equals(obj)));
    }

    @Test
    public void nullMatchesNull() {
        assertThat(null, isSuchThat(o -> o == null));
    }

    @Test
    public void objDoesntMatchNull() {
        Object obj = new Object();

        assertThat(obj, not(isSuchThat(o -> o == null)));
    }

    @Test
    public void nullDoesntMatchObj() {
        Object obj = new Object();
        assertThat(null, not(isSuchThat(o -> o.equals(obj))));
    }

    @Test
    public void argDoesntMatch() {
        Object arg = new Object();
        Object ret = new Object();
        when(ObjGet.getObj(argSuchThat(obj -> !obj.equals(arg)))).thenReturn(ret);

        assertThat(ObjGet.getObj(arg), not(ret));
    }

    @Test
    public void argMatches() {
        Object arg = new Object();
        Object ret = new Object();
        when(ObjGet.getObj(argSuchThat(obj -> obj.equals(arg)))).thenReturn(ret);

        assertThat(ObjGet.getObj(arg), is(ret));
    }

    private static class ObjGet {
        public Object getObj(Object obj) {
            return obj;
        }
    }
}




