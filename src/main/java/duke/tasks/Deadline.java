package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final String by;
    private final LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param description of the deadline.
     * @param by          when the deadline is.
     * @param isDone      if the task is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.date = null;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description of the deadline.
     * @param by          when the deadline is.
     * @param isDone      if the task is done or not.
     * @param date        date of deadline.
     */
    public Deadline(String description, String by, boolean isDone, LocalDate date) {
        super(description, isDone);
        this.by = by;
        this.date = date;
    }

    public String getBy() {
        return by;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + by + ")";
        }
    }
}

