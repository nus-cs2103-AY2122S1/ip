package duke.exceptions;

/**
 * Exception class that is unique to Duke.
 */
public class DukeIndexException extends Exception {
    public DukeIndexException (String message) {
        super(message);
    }
}
