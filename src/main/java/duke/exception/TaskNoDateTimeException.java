package duke.exception;

/**
 * Thrown when no date and time are provided for certain task descriptions
 */
public class TaskNoDateTimeException extends DukeException {
    private final String error;

    /**
     * Constructs TaskNoDateTimeException object
     *
     * @param task type of task with error
     */
    public TaskNoDateTimeException(String task) {
        super();
        this.error = String.format("OOPS!!! Please enter a date/time for your %s!\n", task);
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        return this.error;
    }
}
