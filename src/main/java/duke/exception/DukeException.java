package duke.exception;

/**
 * The DukeException encapsulates all Exceptions that are thrown when using Duke.
 */
public class DukeException extends Exception {

    private static final String ERROR_EXCLAMATION = "oh no!";

    public DukeException(String msg) {
        super(ERROR_EXCLAMATION + msg);
    }
}
