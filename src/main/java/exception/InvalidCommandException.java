package exception;

/**
 * Exception class for invalid commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor of the InvalidCommandException.
     *
     * @param error The error message.
    */
    public InvalidCommandException(String error) {
        super("[Invalid Command] " + error);
    }
}
