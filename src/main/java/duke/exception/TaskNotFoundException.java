package duke.exception;

/**
 * TaskNotFoundException of DukeException.
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Sorry! I couldn't find what you were looking for!");
    }
}
