package duke.tasks;

import duke.Parser;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event (Task).
 * @author Ruth Poh
 */
public class Event extends Task {

    /**
     * Initializes Event.
     * @param taskstr Task.
     * @param date Date/Time of task.
     */
    public Event(String taskstr, LocalDate date) {
        super(taskstr);
        super.date = date;
        super.time = null;
    }

    /**
     * Constructor to initialize Event. With Date and Time.
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
     * Getter method for date Event occurs at.
     * @return Date that Event occurs at in String form.
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Getter method for time Event occurs
     * @return Time that Event occurs at in String form.
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
     * Getter method for simplified date and time Event occurs at.
     * @return Simplified date and time Event occurs at in String form.
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
     * Converts Event to String for storage.
     * @return Event String for storage.
     */
    @Override
    public String toStorageString() {
        return ("E | " + this.getIsDoneBinary() + " | " + super.taskString + " | " + this.getDateTimeStorage());
    }

    /**
     * Returns string of Event (Task).
     * @return string of Event.
     */
    @Override
    public String toString() {
        Parser parser = new Parser();
        if (this.time == null) {
            return "[E] " + super.toString() + " (at: "
                    + parser.simplifyDate(this.date) + ")";
        } else {
            return "[E] " + super.toString() + " (at: "
                    + parser.simplifyDate(this.date)
                    + " " + parser.simplifyTime(this.time)
                    + ")";
        }
    }
}
