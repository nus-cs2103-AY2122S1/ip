package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a deadline task.
 */
public class Deadline extends Task {

    /** The deadline for the task. */
    protected LocalDate dueDate;

    /**
     * Constructor to initialise a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param dueDate The deadline for the Deadline task.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Gets the by description for the Deadline task.
     *
     * @return The by description for the Deadline task.
     */
    public LocalDate getBy() {
        return this.dueDate;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }
}
