package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh
 */
public class Event extends Task {

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param date Date/Time of task.
     */
    public Event(String taskstr, LocalDate date) {
        super(taskstr);
        super.date = date;
        super.time = null;
    }

    /**
     * Constructor to initialize Event. With time.
     * @param taskstr Task.
     * @param date Date/Time of task.
     * @param time Time of task.
     */
    public Event(String taskstr, LocalDate date, LocalTime time) {
        super(taskstr);
        super.date = date;
        super.time = time;
    }

    /**
     * Returns time Event occurs at, in String form.
     * @return Time Event occurs at.
     */
    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns time Event occurs at.
     * @return
     */
    @Override
    public String getTime() {
        if (this.time == null) {
            return "";
        } else {
            return this.date.toString();
        }
    }

    /**
     * Returns simplified date and time Event occurs at, in String form.
     * @return
     */
    @Override
    public String getDateTimeStorage() {
        if (this.time == null) {
            return this.date.toString();
        } else {
            return this.date.toString() + " | " + this.time.toString();
        }
    }

    /**
     * Returns string of Event (Task).
     * @return string of Event.
     */
    @Override
    public String toString() {
        Parser parser = new Parser();
        if (this.time == null) {
            return "[E] " + super.toString() + "(at: "
                    + parser.simplifyDate(this.date) + ")";
        } else {
            return "[E] " + super.toString() + "(at: "
                    + parser.simplifyDate(this.date)
                    + " " + parser.simplifyTime(this.time)
                    + ")";
        }
    }
}
