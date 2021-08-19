/**
 * Exception to indicate invalid task number entered by user
 * e.g. User only has 3 tasks but enters 4
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException(String error) {
        super(error);
    }
}
