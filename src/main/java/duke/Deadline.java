package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Deadline task constructor
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Parses date into MMM dd yyyy format
     *
     * @return date in MMM dd yyyy format
     */
    public String parseDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Overrides toString method
     *
     * @return description of task and date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate() + ")";
    }
}
