package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that handles deadlines.
 */
public class Deadline extends Task {

    protected static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate by;

    /**
     * Constructs the Deadline object.
     *
     * @param description Description of deadline task.
     * @param by Date of deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Checks whether by is the same date.
     *
     * @param date Date to check for.
     * @return Whether date and by are the same dates.
     */
    public boolean isSameBy(LocalDate date) {
        return by.isEqual(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_FORMATTER) + ")";
    }

    @Override
    public String convertToFileFormat() {
        return "D / " + super.convertToFileFormat() + " / " + by;
    }
}
