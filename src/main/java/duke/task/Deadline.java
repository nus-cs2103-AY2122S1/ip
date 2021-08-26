package duke.task;

import duke.util.DukeDateTime;
import duke.exception.DukeException;

/**
 * Represents a <code>Task</code> with a due date.
 */
public class Deadline extends Task implements Timestampable {
    private final DukeDateTime dueDate;

    public Deadline(String name, DukeDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public Deadline(String name, boolean isDone, DukeDateTime dueDate) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toText() {
        String[] props = new String[]{"D", super.getStatusIcon(), super.getName(), dueDate.toISO()};
        return String.join(" | ", props);
    }

    @Override
    public boolean onSameDayAs(DukeDateTime date) {
        return DukeDateTime.onSameDay(dueDate, date);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
