package duke.exception;

/**
 * Thrown when the user inputs an unknown command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
