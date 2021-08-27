package duke.data.exceptions;

public abstract class DukeException extends Exception {
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
