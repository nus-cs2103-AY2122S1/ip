package duke;

/**
 * The duke.DukeException class inherits Exception and
 * is unique Duke instances.
 */
public class DukeException extends Exception {
    /**
     * Constructs the duke.DukeException object.
     *
     * @param s the message associated with the exception
     */
    public DukeException(String s) {
        super(s);
    }
}
