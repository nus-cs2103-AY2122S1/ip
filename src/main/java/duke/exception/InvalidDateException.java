package duke.exception;

/**
 * Exception that is thrown when user inputs invalid date
 */
public class InvalidDateException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occured
     */
    public InvalidDateException(String errorDetails) {
        super(errorDetails);
    }
}
