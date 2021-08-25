package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline - a task that needs to be
 * done before a specific date/time.
 */
public class Deadline extends Task {
    public static final String IDENTIFIER = "D";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private LocalDateTime date;

    /**
     * Constructor for a deadline.
     *
     * @param description Description of the deadline.
     * @param date        Date of the deadline.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Gets the date of the deadline.
     *
     * @return Date of the deadline.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets the type identifier of a deadline.
     *
     * @return string "D" representing a deadline.
     */
    @Override
    public String getType() {
        return IDENTIFIER;
    }

    /**
     * String representation of a deadline.
     *
     * @return String representation of a deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date.format(FORMATTER));
    }
}
