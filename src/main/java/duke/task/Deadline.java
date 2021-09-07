package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task in the Duke program.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the given description.
     *
     * @param description Description of the Deadline task.
     * @param by          Deadline of the Deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the by argument of this Deadline task.
     *
     * @return the by argument of this Deadline task.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the string representation of this Deadline task to be saved in storage.
     *
     * @return the string representation of this Deadline task to be saved in storage.
     */
    @Override
    public String toStringData() {
        return String.format("D | %s | %s", super.toStringData(), by);
    }

    /**
     * Returns a description of this Deadline task.
     *
     * @return a description of this Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
