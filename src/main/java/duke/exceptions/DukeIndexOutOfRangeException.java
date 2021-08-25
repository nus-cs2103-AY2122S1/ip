package duke.exceptions;

public class DukeIndexOutOfRangeException extends DukeException {
    // raised when index input is out of range
    public DukeIndexOutOfRangeException(String errorMessage) {
        super(errorMessage);
    }
}