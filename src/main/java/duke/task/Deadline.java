package duke.task;

import duke.Parser;

import java.time.LocalDate;

/**
 * A Deadline type task representation for Duke.
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Create a Deadline task.
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

}