/**
 * Exception to indicate when user enters a task without a name
 * e.g. deadline
 */
public class MissingTaskNameException extends DukeException {
    public MissingTaskNameException(String error) {
        super(error);
    }
}
