package duke.exceptions;

/**
 * This is a duke.exceptions.TaskNotFoundException that extends duke.exceptions.DukeException.
 */
public class TaskNotFoundException extends DukeException {

    private final int index;
    private boolean hasIndex;
    public TaskNotFoundException(int index) {
        super("☹ OOPS!!! There is no such task!");
        this.index = index;
        this.hasIndex = true;
    }

    public TaskNotFoundException() {
        super("☹ OOPS!!! There is no such task!");
        this.index = 0;
        this.hasIndex = false;
    }

    @Override
    public String getMessage() {
        if (this.hasIndex) {
            return super.getMessage()
                    + String.format("\n    I can't seem to find the task at number %d !", this.index);
        } else {
            return super.getMessage();
        }
    }

}
