/**
 * InvalidInputException class, subclass of PetalException.
 * Thrown when user enters something unintelligible ("eqwqomdoqmwd")
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
}
