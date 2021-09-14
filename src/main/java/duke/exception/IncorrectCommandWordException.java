package duke.exception;

/**
 * Exception to represent an incorrect command word supplied.
 */
public class IncorrectCommandWordException extends DukeException {
    /**
     * The constructor method for DukeException.
     */
    public IncorrectCommandWordException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
