package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for deadline class.
     *
     * @param description Deadline description.
     * @param by Due date of deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task's details in a String.
     *
     * @return Task's details in a String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns deadline's due date.
     *
     * @return Deadline's due date.
     */
    public LocalDate getDueDate() {
        return by;
    }

    /**
     * Returns task's details in a format that will be stored in the data file.
     *
     * @return Task's details in a format that will be stored in the data file.
     */
    @Override
    public String toStringData() {
        return "D" + super.toStringData() + "|" + by;
    }

    @Override
    public boolean matches(String query) {
        return super.matches(query)
                || by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toLowerCase().contains(query.toLowerCase())
                || query.equalsIgnoreCase("deadline");
    }
}
