package duke.exception;

/**
 * Exception class to handle the marking of non-existing tasks.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor for InvalidIndexException class.
     */
    public InvalidIndexException() {
        super("Sorry >.< but this task does not exist!");
    }
}
