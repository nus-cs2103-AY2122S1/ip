package duke.tasks;

import duke.Parser;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Event Task with a specified task with a date and time (optional).
 * @author Ruth Poh
 */
public class Event extends Task {

    /**
     * Initializes Event.
     * @param taskString String representation of the event
     * @param date Date event occurs
     */
    public Event(String taskString, LocalDate date) {
        super(taskString);
        super.date = date;
        super.time = null;
    }

    /**
     * Initializes Event, with Date and Time.
     * @param taskString String representation of the event
     * @param date Date event occurs
     * @param time Time event occurs
     */
    public Event(String taskString, LocalDate date, LocalTime time) {
        super(taskString);
        super.date = date;
        super.time = time;
    }

    /**
     * Getter method for date.
     * @return Readable string representation of date
     */
    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Getter method for date and time Event occurs at.
     * @return Simplified string form of date and time
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
     * Returns simplified string representation of event.
     * @return Simplified string representation of event
     */
    @Override
    public String toStorageString() {
        return ("E | " + this.getIsDoneBinary() + " | " + super.taskString + " | " + this.getDateTimeStorage());
    }

    /**
     * Returns readable string representation of event.
     * @return Readable string representation of event
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
