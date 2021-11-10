package duke.exception;

/**
 * A specific {@code DukeException} that will be thrown when an input item is not a positive integer.
 */
public class IndexMismatchException extends DukeException {
    /**
     * Constructs an {@code IndexMismatchException}.
     */
    public IndexMismatchException() {
        super("OOPS!!! The item should be an positive integer.");
    }
}
