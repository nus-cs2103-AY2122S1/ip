/**
 * Exception to indicate when user enters a task without a name
 * e.g. deadline
 */
public class NoNameException extends DukeException {
    public NoNameException(String error) {
        super(error);
    }
}
