package duke.exception;

/**
 * A runtime exception that will be thrown if the user gives an incorrect command at the command parser.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
