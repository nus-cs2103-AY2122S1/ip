package duke.exception;

/**
 * Exception that is thrown when user inputs something invalid in general
 */
public class DukeException extends Exception {
    public DukeException(String explanation) {
        super(explanation);
    }
}
