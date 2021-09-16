package taskMan.exception;

/**
 * Exception that is thrown when user inputs invalid date
 */
public class InvalidDateException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception that is thrown when user inputs invalid date
     */
    public InvalidDateException(String errorDetails) {
        super(errorDetails);
    }
}
