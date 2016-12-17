package org.wesss.general_utils.reflection;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class ObjectMirrorTest {

    private FakeObject mockFakeObject;
    private ObjectMirror objMirror;

    public ObjectMirrorTest() {
        mockFakeObject = mock(FakeObject.class);
    }

    @Before
    public void before() {
        reset(mockFakeObject);
        objMirror = new ObjectMirror(mockFakeObject);
    }

    @Test
    public void objectMirrorCallsMethod() throws Throwable {
        Method voidMethod = FakeObject.class.getDeclaredMethod("voidMethod");
        objMirror.call(voidMethod);

        verify(mockFakeObject).voidMethod();
    }

    // TODO finish object mirror call functionality

    private class FakeObject {

        public void voidMethod() {

        }

        public int returnPrimitiveMethod() {
            return 0;
        }

        public Object returnObjectMethod() {
            return null;
        }

        public void primitiveParamMethod(int param) {

        }

        public void ObjectParamMethod(Object param) {

        }

        public void multipleParamMethod(int param1, int param2) {

        }

        public void throwsRuntimeExceptionMethod() {
            throw new RuntimeException("Test Exception");
        }
    }
}
