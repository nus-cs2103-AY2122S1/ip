package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks of type deadlines, inherit from Task abstract class.
 * Deadlines have a description, date and time.
 */
public class Deadlines extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor of the Deadlines class.
     *
     * @param description description of a deadline in String.
     * @param date date when deadline is due.
     *                  Must be in the format yyyy-mm-dd.
     * @param time time point when deadline is due.
     *                 Must be in the format hh:mm in 24-hours time.
     */
    public Deadlines(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the deadline in a string for print.
     *
     * @return Deadline represented in a string for print,
     * in the format: [D] [state] description (by date time)
     */
    @Override
    public String toString() {
        return String.format("[D] [%s] " + this.description + " (by: %s %s)",
            this.getStatusIcon(),
            this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
            this.time);
    }

    /**
     * Returns the deadline in a string for sending to the data file to save.
     *
     * @return Deadline represented in a string for saving in data file,
     * in the format: D|state|description|date|time
     */
    @Override
    public String toDataFileString() {
        return String.format("D|%s|%s|%s|%s",
            this.isDone ? "1" : "0",
            this.description,
            this.date,
            this.time);
    }

    /**
     * Sets the date of deadline.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the time of deadline.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
}
