package duke.exception;

/**
 * Thrown when no descriptions are provided for tasks
 */
public class EmptyDescriptionException extends DukeException {
    private String task;

    /**
     * Constructs EmptyDescriptionException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public EmptyDescriptionException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! The description of a %s cannot be empty.\n", task);
    }
}
