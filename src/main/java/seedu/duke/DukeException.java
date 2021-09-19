package seedu.duke;

/**
 * Represents a DukeException. A <code>DukeException</code> is thrown
 * when an error occured when executing Duke functions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
