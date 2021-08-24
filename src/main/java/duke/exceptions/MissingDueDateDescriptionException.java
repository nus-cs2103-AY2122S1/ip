package duke.exceptions;

public class MissingDueDateDescriptionException extends DukeException {

    public MissingDueDateDescriptionException() { }

    @Override
    public String toString() {
        return "OOPS!!! The due date of a deadline cannot be empty.";
    }
}
