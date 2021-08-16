/**
 * InvalidInputException class, subclass of PetalException.
 * Thrown when user enters something unintelligible ("eqwqomdoqmwd")
 * or when the user enters the wrong format ("deadline go for a run /at 6pm")
 */
public class InvalidInputException extends PetalException {

    //The exception message
    private String message;
    //Initialized if this exception is used as a wrapper
    private Throwable cause;

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
}
