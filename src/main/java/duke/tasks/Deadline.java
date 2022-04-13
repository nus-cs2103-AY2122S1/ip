package duke.tasks;

import duke.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Deadline Task with a specified task with a date and time (optional).
 * @author Ruth Poh
 */
public class Deadline extends Task {

    /**
     * Initializes Deadline.
     * @param taskString String representation of the deadline
     * @param date Date of deadline
     */
    public Deadline(String taskString, LocalDate date) {
        super(taskString);
        super.date = date;
        super.time = null;
    }

    /**
     * Initializes Deadline, with Date and Time.
     * @param taskString String representation of the deadline
     * @param date Date deadline is done by
     * @param time Time deadline is done by
     */
    public Deadline(String taskString, LocalDate date, LocalTime time) {
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
     * Getter method for date and time Deadline occurs by.
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
     * Returns simplified string representation of deadline.
     * @return Simplified string representation of deadline
     */
    @Override
    public String toStorageString() {
        return ("D | " + this.getIsDoneBinary() + " | " + super.taskString + " | " + this.getDateTimeStorage());
    }

    /**
     * Returns readable string representation of deadline.
     * @return Readable string representation of deadline
     */
    @Override
    public String toString() {
        Parser parser = new Parser();
        if (this.time == null) {
            return "[D] " + super.toString() + " (by: "
                    + parser.simplifyDate(this.date) + ")";
        } else {
            return "[D] " + super.toString() + " (by: "
                    + parser.simplifyDate(this.date)
                    + " " + parser.simplifyTime(this.time)
                    + ")";
        }
    }
}
