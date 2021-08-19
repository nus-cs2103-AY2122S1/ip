package Duke.Task;

import Duke.DukeException;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String message) {
        super("Invalid task: " + message);
    }
}
