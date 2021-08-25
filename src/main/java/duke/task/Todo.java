package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {

    private static final String label = "T";

    /**
     * Constructs a Todo with the specified description.
     *
     * @param description Description of the todo.
     * @throws DukeException If the description does not follow the format.
     */
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




