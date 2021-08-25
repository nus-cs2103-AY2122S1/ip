package seedu.duke;

/**
 * Represents a DukeException. A <code>DukeException</code> is thrown
 * when an error occured when executing Duke functions.
 */
class DukeException extends Exception {
    protected DukeException(String message) {
        super(message);
    }
}
