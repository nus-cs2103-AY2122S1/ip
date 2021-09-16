package duke.exception;

/**
 * Thrown when description of task is invalid
 */
public class InvalidDescriptionException extends DukeException {
    private final String error;

    /**
     * Constructs InvalidDescriptionException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public InvalidDescriptionException(String msg, String task) {
        super(msg);
        this.error = String.format("OOPS!!! Please enter a proper description for %s", task);
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
