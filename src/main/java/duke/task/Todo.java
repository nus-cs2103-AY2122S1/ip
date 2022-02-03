package duke.task;

import duke.DukeException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the Todo task.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Returns the String representation of the Todo task as used in the storage.
     *
     * @return String representation of the Todo task in the storage format.
     */
    @Override
    public String toStorageString() {
        return ("T|" + super.getStatusNumber() + "|" + super.getDescription());
    }

    @Override
    public String toUndoneString() {
        return ("[T]" + super.toUndoneString());
    }
}
