package memocat.task;

import java.time.LocalDate;

import memocat.Parser;

/**
 * A Deadline type task representation for memocat.
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the deadline.
     * @param by LocalTime that the deadline is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new Parser().formatLocalDate(by) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline) {
            Deadline otherDdl = (Deadline) other;
            return this.description.equals(otherDdl.description)
                    && (this.isDone == otherDdl.isDone) && this.by.equals(otherDdl.by);
        }
        return false;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            // always put todo task before other types
            // do not sort it for now
            return -1;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return this.by.compareTo(eventTask.at);
        } else {
            assert task instanceof Deadline : "Invalid Task type: " + task;
            Deadline deadlineTask = (Deadline) task;
            return this.by.compareTo(deadlineTask.by);
        }
    }
}
