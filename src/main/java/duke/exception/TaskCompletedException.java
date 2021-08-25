package duke.exception;

import duke.exception.DukeException;

public class TaskCompletedException extends DukeException {
    public TaskCompletedException(String errorMessage) {
        super(errorMessage);
    }
}
