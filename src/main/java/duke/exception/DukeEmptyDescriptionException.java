package duke.exception;

public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException() {
        super("The description of a task cannot be empty.");
    }
}
