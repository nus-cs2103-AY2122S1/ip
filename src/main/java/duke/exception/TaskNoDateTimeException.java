package duke.exception;

/**
 * Thrown when no date and time are provided for certain task descriptions
 */
public class TaskNoDateTimeException extends DukeException {
    private String task;

    /**
     * Constructs TaskNoDateTimeException object
     *
     * @param msg error message
     * @param task type of task with error
     */
    public TaskNoDateTimeException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    /**
     * Prints error message
     */
    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! Please enter a date/time for your %s!\n", task);
    }
}
