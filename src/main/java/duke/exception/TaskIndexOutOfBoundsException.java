package duke.exception;

public class TaskIndexOutOfBoundsException extends DukeException {

    public TaskIndexOutOfBoundsException() {
        super("Invalid task index specified!");
    }
}
