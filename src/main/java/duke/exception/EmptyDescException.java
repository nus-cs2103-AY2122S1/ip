package duke.exception;

import duke.Task;

/**
 * The exception thrown when the user attempts to create a task without providing a non-empty description.
 */
public class EmptyDescException extends DukeException {
    private final Task.Type type;

    public EmptyDescException(Task.Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "The description of a " + type + " cannot be empty!";
    }
}
