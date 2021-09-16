package duke.exception;

/**
 * Thrown when user tries to delete or complete a task that does not exist
 */
public class TaskNotFoundException extends DukeException {
    private final String error;

    /**
     * Constructs TaskNotFoundException object
     *
     * @param msg error message
     */
    public TaskNotFoundException(String msg) {
        super(msg);
        this.error = "OOPS!!! I cannot find this task! Please select an existing task number.";
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
