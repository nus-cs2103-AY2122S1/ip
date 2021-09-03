package duke.exception;

/**
 * Thrown when no descriptions are provided for tasks
 */
public class EmptyDescriptionException extends DukeException {
    private String error;

    /**
     * Constructs EmptyDescriptionException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public EmptyDescriptionException(String msg, String task) {
        super(msg);
        this.error = String.format("OOPS!!! The description of a %s cannot be empty.\n", task);
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.printf(error);
    }

    @Override
    public String getError() {
        return this.error;
    }

}
