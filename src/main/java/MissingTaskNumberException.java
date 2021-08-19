/**
 * Exception to indicate when the user forgets to enter task number
 */
public class MissingTaskNumberException extends DukeException {
    public MissingTaskNumberException(String error) {
        super(error);
    }
}
