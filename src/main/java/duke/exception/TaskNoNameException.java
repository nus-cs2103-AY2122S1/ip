package duke.exception;

/**
 * Thrown when name of task not provided for certain task descriptions
 */
public class TaskNoNameException extends DukeException {
    private final String ERROR;

    /**
     * Constructs TaskNoNameException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public TaskNoNameException(String msg, String task) {
        super(msg);
        this.ERROR = String.format("OOPS!!! Please enter a name for your %s!\n", task);
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        return this.ERROR;
    }
}
