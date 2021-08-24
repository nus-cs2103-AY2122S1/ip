package duke.exception;

/**
 * A subclass of DukeException. Thrown when users gives task number that is out of bound.
 *
 */
public class OutOfBoundException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public OutOfBoundException() {
        super("Task does not exist. Please send a correct task number ><");
    }
}
