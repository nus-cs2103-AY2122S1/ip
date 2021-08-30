package duke.exceptions;

/**
 * This is a TaskNotFoundException that extends DukeException.
 * This exception is thrown when the task at specified index is not found.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * This is the class field of TaskNotFoundException.
     */
    private final int index;
    private final boolean hasIndex;

    /**
     * This is the TaskNotFoundException constructor.
     *
     * @param index An index representing the task the user attempts to find, but does not exist.
     */
    public TaskNotFoundException(int index) {
        super("OOPS!!! There is no such task!");
        this.index = index;
        this.hasIndex = true;
    }

    /**
     * This is an Overloaded TaskNotFoundException for no index specified.
     */
    public TaskNotFoundException() {
        super("OOPS!!! There is no such task!");
        this.index = 0;
        this.hasIndex = false;
    }

    @Override
    public String getMessage() {
        if (this.hasIndex) {
            return super.getMessage()
                    + String.format("\nI can't seem to find the task at number %d !", this.index);
        } else {
            return super.getMessage();
        }
    }

}
