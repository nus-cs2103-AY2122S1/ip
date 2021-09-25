package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user input invalid argument
 * for the command input by the user.
 */
public class InvalidArgumentException extends RuntimeException {
    /**
     * Default constructor of InvalidArgumentException.
     *
     * @param errorMessage message to be stored in the InvalidArgumentException.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
