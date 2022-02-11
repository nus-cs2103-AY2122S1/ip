package duke.exception;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Please specify a task number! Please try again.");
    }
}
