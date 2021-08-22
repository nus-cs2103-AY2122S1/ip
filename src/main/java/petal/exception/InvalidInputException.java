package petal.exception;

import petal.components.Responses;

/**
 * InvalidInputException class, subclass of PetalException.
 * Thrown when user enters something unintelligible ("random command")
 * or when the user enters the wrong format ("deadline go for a run /at 6pm")
 */
public class InvalidInputException extends PetalException {

    /**
     * Constructor for InvalidInputException
     * @param message The exception message
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Constructor for InvalidInputException (Used as wrapper)
     * @param message The exception message
     * @param cause Initial cause
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for InvalidInputException (Used as wrapper)
     * @param response The exception message
     * @param cause Initial cause
     */
    public InvalidInputException(Responses response, Throwable cause) {
        super(response.toString(), cause);
    }

}
