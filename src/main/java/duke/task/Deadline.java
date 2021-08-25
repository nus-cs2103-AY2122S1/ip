package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class creates deadline instances which are to be done by a certain time.
 */
public class Deadline extends Task {
    // The date to complete the deadline by.
    protected LocalDate date;
    // The time of the date to complete the deadline by.
    protected String time = "";

    /**
     * Constructor to create a deadline.
     *
     * @param name The name of the deadline.
     * @param date The date to complete the deadline by.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Secondary constructor to which includes time.
     *
     * @param name The name of the deadline.
     * @param date The date of the deadline.
     * @param time The time of the deadline.
     */
    public Deadline(String name, LocalDate date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The name of the deadline.
     */
    @Override
    public String toString() {
        String timeComponent = (this.time.equals("")
                ? this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                : this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.time);
        return "[D]" + super.toString() + " (by: "
                + timeComponent
                + ")";
    }
}