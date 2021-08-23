package duke.exception;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Invalid task number, please try again.");
    }
}
