package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks of type deadlines, inherit from Task abstract class.
 * Deadlines have a description, date and time.
 */
public class Deadlines extends Task {
    protected LocalDate localDate;
    protected LocalTime localTime;

    /**
     * Constructor of the Deadlines class.
     *
     * @param description description of a deadline in String.
     * @param localDate date when deadline is due.
     *                  Must be in the format yyyy-mm-dd.
     * @param localTime time point when deadline is due.
     *                 Must be in the format hh:mm in 24-hours time.
     */
    public Deadlines(String description, LocalDate localDate, LocalTime localTime) {
        super(description);
        this.localDate = localDate;
        this.localTime = localTime;
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
            this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
            this.localTime);
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
            this.localDate,
            this.localTime);
    }
}
