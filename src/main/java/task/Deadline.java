package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Date of the Deadline */
    protected LocalDate date;
    /** Time of the Deadline. */
    protected LocalTime time;

    /**
     * Constructor for Deadline.
     * @param description Text description of Deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Override toString method to show representation of Deadline.
     * @return String representing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
