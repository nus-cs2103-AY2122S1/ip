package petal.exception;

import petal.components.Responses;

/**
 * EmptyDescException class. Subclass of PetalException.
 * Thrown when user does not give a description for Event/Deadline.
 * Example: "deadline /by 8pm"
 */
public class EmptyDescException extends PetalException {

    /**
     * Constructor for the EmptyDescException
     *
     * @param message The exception message
     */
    public EmptyDescException(String message) {
        super(message);
    }

    /**
     * Constructor for the EmptyDescException
     *
     * @param message The exception message
     */
    public EmptyDescException(Responses message) {
        super(message.toString());
    }

    /**
     * Constructor for the EmptyDescException (Used as wrapper)
     *
     * @param message The exception message
     * @param cause Initial Cause
     */
    public EmptyDescException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for the EmptyDescException (Used as wrapper)
     *
     * @param response The exception message
     * @param cause Initial Cause
     */
    public EmptyDescException(Responses response, Throwable cause) {
        super(response.toString(), cause);
    }

}
