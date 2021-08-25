package duke.exception;

import duke.exception.DukeException;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
