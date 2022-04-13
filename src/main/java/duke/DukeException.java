package duke;

/**
 * The DukeException class inherits Exception and
 * is unique Duke instances.
 */
public class DukeException extends Exception {
    /**
     * Constructs the duke.DukeException object.
     *
     * @param message the message associated with the exception
     */
    public DukeException(String message) {
        super(message);
    }
}
