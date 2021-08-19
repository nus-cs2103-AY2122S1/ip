package Duke.Task;

import Duke.DukeException;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("Task description cannot be empty.");
    }
}
