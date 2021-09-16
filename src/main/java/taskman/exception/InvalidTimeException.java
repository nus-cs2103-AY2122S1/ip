package taskman.exception;

/**
 * Exception that is thrown when user inputs invalid time
 */
public class InvalidTimeException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception that is thrown when user inputs invalid time
     */
    public InvalidTimeException(String errorDetails) {
        super(errorDetails);
    }
}
