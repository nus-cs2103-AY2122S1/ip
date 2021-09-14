package duke.exception;

/**
 * Thrown when no descriptions are provided for tasks
 */
public class EmptyDescriptionException extends DukeException {
    private final String ERROR;

    /**
     * Constructs EmptyDescriptionException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public EmptyDescriptionException(String msg, String task) {
        super(msg);
        this.ERROR = String.format("OOPS!!! The description of a %s cannot be empty.\n", task);
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
