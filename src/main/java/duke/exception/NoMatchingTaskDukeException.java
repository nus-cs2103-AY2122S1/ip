package duke.exception;

public class NoMatchingTaskDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "No matching tasks found.";
    public NoMatchingTaskDukeException() {
        super(ERROR_MESSAGE);
    }
}
