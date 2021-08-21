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

    /**
     * This is the TaskNotFoundException constructor.
     *
     * @param index An index representing the task the user attempts to find, but does not exist.
     */
    public TaskNotFoundException(int index) {
        super("☹ OOPS!!! There is no such task!");
        this.index = index;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + String.format("\n    I can't seem to find the task at number %d !", this.index);
    }

}
