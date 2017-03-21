package org.wesss.general_utils.exceptions;

/**
 * Represents a misuse of code
 */
public class IllegalUseException extends RuntimeException {

    public IllegalUseException() {
        super();
    }

    public IllegalUseException(String message) {
        super(message);
    }
}
