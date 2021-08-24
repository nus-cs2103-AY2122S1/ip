package duke.exceptions;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("☹ Sorry! There is no such task with this task index!");
    }
}