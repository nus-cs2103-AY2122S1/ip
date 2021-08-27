package duke.exception;

/**
 * Thrown when name of task not provided for certain task descriptions
 */
public class TaskNoNameException extends DukeException {
    private String task;

    /**
     * Constructs TaskNoNameException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public TaskNoNameException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! Please enter a name for your %s!\n", task);
    }
}
