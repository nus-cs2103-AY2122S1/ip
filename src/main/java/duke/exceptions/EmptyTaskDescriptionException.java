package duke.exceptions;

/**
 * The condition where a user creates a task without giving a task name.
 */
public class EmptyTaskDescriptionException extends DukeException {
    /**
     * Creates an empty task description exception instance
     * with a standard detail message.
     */
    public EmptyTaskDescriptionException() {
        super("Description of a task cannot be empty!");
    }
}
