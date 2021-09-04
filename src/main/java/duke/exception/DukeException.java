package duke.exception;

/**
 * The DukeException encapsulates all Exceptions that are thrown when using Duke.
 * It is also used for exceptions that are not covered by its subclasses.
 */
public class DukeException extends Exception {

    private static final String ERROR_EXCLAMATION = "oh no! ";

    public DukeException(String msg) {
        super(ERROR_EXCLAMATION + msg);
    }
}
