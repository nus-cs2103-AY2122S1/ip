package duke.exception;

public class TaskNotFoundDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Task %d does not exist.";
    public TaskNotFoundDukeException(int taskId) {
        super(String.format(ERROR_MESSAGE, taskId));
    }
}
