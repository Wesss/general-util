package org.wesss.general_utils.reflection;

import org.junit.internal.runners.model.ReflectiveCallable;

import java.lang.reflect.Method;

/**
 *
 */
public class ObjectMirror {

    //TODO document objectMirror

    Object object;

    public ObjectMirror(Object object) {
        this.object = object;
    }

    /**
     *
     * @param method
     * @return
     * @throws Throwable
     */
    public Object call(Method method, Object... params) throws Throwable {
        return new ReflectiveCallable() {
            @Override
            protected Object runReflectiveCall() throws Throwable {
                return method.invoke(object, params);
            }
        }.run();
    }
}
