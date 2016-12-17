package org.wesss.general_utils.reflection;

import org.junit.internal.runners.model.ReflectiveCallable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefectionUtils {

    /**
     * Invokes the given method, throwing any throws it makes instead of throwing a
     * {@link InvocationTargetException}
     * @param method method to run
     * @param target object that is to run method
     * @param params parameters to method call
     * @return the returned value of given method
     * @throws Throwable rethrown from given method
     */
    public static Object invokeUnwrappingThrowables(Method method, Object target, Object... params)
            throws Throwable {
        return new ObjectMirror(target).call(method, params);
    }
}
