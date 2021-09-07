package duke.exception;

/**
 * Exception that is thrown when user inputs missing time
 */
public class EmptyTimeException extends DukeException{

    /**
     * Basic Constructor
     *
     * @param errorDetails Explanation as to why error occured
     */
    public EmptyTimeException (String errorDetails) {
        super(errorDetails);
    }
}
