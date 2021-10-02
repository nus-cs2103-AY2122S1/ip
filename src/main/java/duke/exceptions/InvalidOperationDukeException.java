package duke.exceptions;

/** DukeException representing an attempt to do something that is not supported on existing tasks */
public class InvalidOperationDukeException extends DukeException {
    public InvalidOperationDukeException(String message) {
        super(message);
    }
}
