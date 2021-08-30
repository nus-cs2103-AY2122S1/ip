package duke.exceptions;

/**
 * Encapsulates the TaskNotFoundException thrown by the Duke bot.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructor for TaskNotFoundException.
     */
    public TaskNotFoundException() {
        super("Sorry! There is no such task with this task index!");
    }
}
