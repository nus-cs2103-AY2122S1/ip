package duke;

/**
 * An Exception unique to the functionality of the Duke program.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public DukeException() {
        super("");
    }
}
