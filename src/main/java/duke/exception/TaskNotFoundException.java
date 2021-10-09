package duke.exception;

/**
 * Encapsulates the exception when the user tries to find a task that doesn't exist.
 */
public class TaskNotFoundException extends DukeException {

    /**
     * Represents a constructor for the TaskNotFoundException class.
     */
    public TaskNotFoundException() {
        super("Sorry, the task you are looking for doesn't exist!");
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
