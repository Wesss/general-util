package org.wesss.general_utils.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefectionUtils {

    /**
     * Invokes the given method, rethrowing any exceptions caught instead of throwing a
     * {@link InvocationTargetException}
     * @param method method to run
     * @param target object that is to run method
     * @param params parameters to method call
     * @return the returned value of given method
     * @throws Throwable rethrown from given method
     */
    public static Object invokeRethrowingThrowables(Method method, Object target, Object... params)
            throws Throwable {
        return new ObjectMirror(target).call(method, params);
    }

    /**
     * Invokes the given method, rethrowing any exceptions caught wrapped in a
     * {@link RuntimeException} instead of throwing a {@link InvocationTargetException}
     * @param method method to run
     * @param target object that is to run method
     * @param params parameters to method call
     * @return the returned value of given method
     * @throws RuntimeException containing the exception thrown from given method
     */
    public static Object invokeRethrowingInRuntimeException(Method method,
                                                            Object target,
                                                            Object... params) {
        try {
            return new ObjectMirror(target).call(method, params);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
