package org.wesss.general_utils.language;

/**
 * Represents a reference to an object. This is intended to be used similarly to a return pointer or to allow for
 * changing of immutable objects without losing reference
 * @param <T> the type of the reference
 */
public class Reference<T> {
    private T reference;

    public Reference() {
        this.reference = null;
    }

    public Reference(T reference) {
        this.reference = reference;
    }

    public void setReference(T reference) {
        this.reference = reference;
    }

    public T dereference() {
        if (reference == null) {
            throw new NullPointerException("reference has not been set");
        }
        return reference;
    }
}
