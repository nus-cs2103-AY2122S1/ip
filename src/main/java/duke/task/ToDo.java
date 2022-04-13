package duke.task;

import duke.DukeException;

/**
 * Represents a Task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task.
     *
     * @param description The description of this todo.
     * @throws DukeException
     */
    public ToDo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the todo.");
        }
    }

    /**
     * Returns a string representation of this task to be displayed.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this task to be written into data storage.
     *
     * @param delimiter The delimiter used by the storage to parse data fields.
     * @return The data string representation of this task.
     */
    @Override
    public String toDataString(String delimiter) {
        String tag = "T";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description);
    }
}
