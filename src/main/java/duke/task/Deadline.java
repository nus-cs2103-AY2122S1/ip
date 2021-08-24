package duke.task;

import duke.DukeException;

public class Deadline extends DateTask {
    public Deadline(String description, String by) throws DukeException {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, by, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + getDateString();
    }
}
