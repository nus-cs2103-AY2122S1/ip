package duke.task;

import duke.DukeException;

public class InvalidTaskException extends DukeException {
    private static final String INVALID_TASK_MESSAGE = "Invalid task: %s";

    public InvalidTaskException(String message) {
        super(String.format(INVALID_TASK_MESSAGE, message));
    }

    public InvalidTaskException(String message, Throwable cause) {
        super(String.format(INVALID_TASK_MESSAGE, message), cause);
    }
}
