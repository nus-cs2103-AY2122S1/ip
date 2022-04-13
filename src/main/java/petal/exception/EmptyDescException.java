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
    public EmptyDescException(Responses message) {
        super(message.toString());
    }

}
