/**
 * Exception to indicate an unknown command entered by user
 * e.g. dateline
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String error) {
        super(error);
    }
}
