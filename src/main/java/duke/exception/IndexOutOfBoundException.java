package duke.exception;

/**
 * A specific {@code DukeException} that will be thrown when an input item is out of bound.
 */
public class IndexOutOfBoundException extends DukeException {
    /**
     * Constructs an {@code IndexOutOfBoundException}.
     */
    public IndexOutOfBoundException() {
        super("OOPS!!! The item index you filled in is out of bound!");
    }
}
