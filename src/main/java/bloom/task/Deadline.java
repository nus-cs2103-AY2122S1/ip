package bloom.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    /** The date and time of the deadline. */
    protected final LocalDateTime by;

    /**
     * Constructor for a Deadline.
     *
     * @param description the description of the deadline
     * @param by          the date and time of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the string representation of the deadline.
     *
     * @return a string representing the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDate() + ")";
    }

    /**
     * Converts to the format used for database storage.
     *
     * @return a string representing the deadline
     */
    @Override
    public String toDb() {
        return "D | " + super.toDb() + " | " + this.by;
    }

    public LocalDateTime getDate() {
        return by;
    }

    /**
     * Prints date of a deadline.
     *
     * @return a string date of format MMM dd yyyy
     */
    @Override
    public String printDate() {
        int y = by.getYear();
        int m = by.getMonthValue();
        int d = by.getDayOfMonth();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String month = months[m - 1];

        return month + " " + d + " " + y;
    }
}
