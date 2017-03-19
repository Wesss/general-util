package org.wesss.general_utils.fluentstyle;

import org.wesss.general_utils.exceptions.IllegalUseException;

/**
 * A token that can be used once.
 * On attempting to use a second time, an exception is thrown
 */
public class OneTimeUseToken {

    private boolean isUsed;

    public OneTimeUseToken() {
        isUsed = false;
    }

    /**
     * @throws IllegalUseException iff this has already been used
     */
    public void use() {
        if (isUsed) {
            throw new IllegalUseException();
        }

        isUsed = true;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
