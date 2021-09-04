package duke.exception;

import duke.Task;

public class EmptyDateException extends DukeException {
    private final Task.Type type;

    public EmptyDateException(Task.Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "The " + type + " must have a date / time!";
    }
}
