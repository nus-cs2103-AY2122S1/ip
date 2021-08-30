package duke;

/**
 * The DukeException class inherits Exception and
 * is unique Duke instances.
 */
public class DukeException extends Exception {
    /**
     * Constructs the DukeException object.
     *
     * @param s the message associated with the exception
     */
    public DukeException(String s) {
        super(s);
    }
}
