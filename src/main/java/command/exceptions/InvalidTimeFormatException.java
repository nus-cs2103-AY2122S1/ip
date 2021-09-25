package command.exceptions;

/**
 * Exception to do with Command to be thrown when the user input invalid time
 * format as a command.
 */
public class InvalidTimeFormatException extends RuntimeException {
    /**
     * Default constructor of InvalidTimeException.
     *
     * @param errorMessage message to be stored in the InvalidTimeException.
     */
    public InvalidTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
