package duke.exceptions;

public class DukeIllegalFormatException extends DukeException {
    // raised when user input format is incorrect
    public DukeIllegalFormatException(String errorMessage) {
        super(errorMessage);
    }
}
