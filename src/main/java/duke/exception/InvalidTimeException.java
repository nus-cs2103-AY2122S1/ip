package duke.exception;

/**
 * Exception that is thrown when user inputs invalid time
 */
public class InvalidTimeException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occured
     */
    public InvalidTimeException(String errorDetails) {
        super(errorDetails);
    }
}
