package duke.exception;

public class TaskDoesNotExistException extends DukeException {
    public TaskDoesNotExistException() {
        super("This task does not exist! Please try again.");
    }
}
