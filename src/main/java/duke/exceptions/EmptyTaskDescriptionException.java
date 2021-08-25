package duke.exceptions;

public class EmptyTaskDescriptionException extends DukeException {
    public EmptyTaskDescriptionException() {
        super("Please provide a task description! ☹");
    }
}
