package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param at Date/Time of task.
     */
    public Event(String taskstr, LocalDate at) {
        super(taskstr);
        this.at = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        super.date = at;
    }

    /**
     * Returns time Event occurs at, in String form.
     * @return Time Event occurs at.
     */
    @Override
    public String getTime() {
        return this.at;
    }

    /**
     * Returns simplified time Event occurs at, in String form.
     * @return Simplified time Event occurs at.
     */
    @Override
    public String getTimeStorage() {
        return this.date.toString();
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at: " + this.at + ")";
    }
}
