package duke.exception;

/**
 * This is the parent class for all Duke exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
