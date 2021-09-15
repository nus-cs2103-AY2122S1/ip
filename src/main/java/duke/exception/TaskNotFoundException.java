package duke.exception;

import duke.Duke;

public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("Task does not exist!");
    }
}
