package duke.exception;

/**
 * Exception that is thrown when user inputs missing description
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception that is thrown when user inputs missing description
     */
    public EmptyDescriptionException(String errorDetails) {
        super(errorDetails);
    }
}
