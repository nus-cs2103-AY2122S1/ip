package petal.exception;

import petal.components.Responses;

/**
 * EmptyDescException class. Subclass of PetalException.
 * Thrown when user does not give a description for Event/Deadline.
 * Example: "deadline /by 8pm"
 */
public class EmptyDescException extends PetalException {

    /**
     * Constructs an EmptyDescException
     *
     * @param message The exception message
     */
    public EmptyDescException(String message) {
        super(message);
    }

    /**
     * Constructs an EmptyDescException
     *
     * @param message The exception message
     */
    public EmptyDescException(Responses message) {
        super(message.toString());
    }

    /**
     * Constructs an EmptyDescException (used as a wrapper)
     *
     * @param message The exception message
     * @param cause Initial Cause
     */
    public EmptyDescException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an EmptyDescException (used as a wrapper)
     *
     * @param response The exception message
     * @param cause Initial Cause
     */
    public EmptyDescException(Responses response, Throwable cause) {
        super(response.toString(), cause);
    }

}
