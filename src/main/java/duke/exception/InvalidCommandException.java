package duke.exception;

/**
 * A subclass of DukeException. Thrown when users do not give a description to the task.
 *
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Invalid Command!");
    }
}
