package taskman.exception;

/**
 * Exception that is thrown when user inputs missing time
 */
public class EmptyTimeException extends DukeException {

    /**
     * Basic Constructor
     *
     * @param errorDetails Exception that is thrown when user inputs missing time
     */
    public EmptyTimeException (String errorDetails) {
        super(errorDetails);
    }
}
