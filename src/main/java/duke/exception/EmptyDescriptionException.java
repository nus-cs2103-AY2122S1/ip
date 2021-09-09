package duke.exception;

/**
 * Exception that is thrown when user inputs missing description
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occurred
     */
    public EmptyDescriptionException(String errorDetails) {
        super(errorDetails);
    }
}
