/**
 * Exception to indicate when user enters deadline without due date
 * e.g. deadline math homework
 */
public class MissingDeadlineException extends DukeException {
    public MissingDeadlineException(String error) {
        super(error);
    }
}
