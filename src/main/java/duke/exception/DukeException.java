package duke.exception;

/**
 * Exception that is thrown when user inputs something invalid in general
 */
public class DukeException extends Exception {

    /**
     * Basic Constructor
     * @param errorDetails Explanation as to why error occurred
     */
    public DukeException(String errorDetails){
        super(errorDetails);

    }
}
