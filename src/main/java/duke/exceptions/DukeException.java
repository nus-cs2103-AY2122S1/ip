package duke.exceptions;

/**
 * Class of exception that handles all exceptions that
 * occur in the running of duke bot
 */
public class DukeException extends Exception {

    /**
     * Instantiates a duke exception 
     * capturing all errors that may occur in duke
     * 
     * @param exceptionMsg String error message 
     */
    public DukeException(String exceptionMsg) {
        super(exceptionMsg);
    }
}