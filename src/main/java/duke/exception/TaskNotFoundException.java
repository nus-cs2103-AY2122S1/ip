package duke.exception;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
