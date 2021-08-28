package duke.exception;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("There is no such tasks with this task number!");
    }
}
