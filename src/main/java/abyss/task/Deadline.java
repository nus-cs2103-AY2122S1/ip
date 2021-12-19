package abyss.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a description and date.
 */
public class Deadline extends Task {
    protected LocalDate date;

    /**
     * Constructs a deadline.
     *
     * @param description Description of the deadline.
     * @param by Date of deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns formatted details of the deadline.
     *
     * @return Formatted deadline.
     */
    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns details of the deadline formatted for file entry.
     *
     * @return Formatted deadline.
     */
    @Override
    public String toFileEntry() {
        return "D | " + super.getIsDone() + " | " + super.description + " | " + this.date;
    }
}
