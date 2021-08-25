package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {

    private static final String label = "T";

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + label + "]" + super.toString();
    }

    @Override
    public String toDataString() {
        return label + super.toDataString();
    }
}




