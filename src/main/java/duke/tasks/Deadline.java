package duke.tasks;

import duke.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline (Task).
 * @author Ruth Poh
 */
public class Deadline extends Task {

    /**
     * Initializes Deadline.
     * @param taskString Task.
     * @param date Date of deadline.
     */
    public Deadline(String taskString, LocalDate date) {
        super(taskString);
        super.date = date;
        super.time = null;
    }

    /**
     * Initialize Deadline, with time.
     * @param taskString Task.
     * @param deadline Deadline of task.
     * @param time Time of task.
     */
    public Deadline(String taskString, LocalDate deadline, LocalTime time) {
        super(taskString);
        super.date = deadline;
        super.time = time;
    }

    /**
     * Getter method for date Deadline occurs at.
     * @return Date that Deadline occurs at in String form.
     */
    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Getter method for time Deadline occurs at.
     * @return Time that Deadline occurs at in String form.
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
     * Getter method for simplified date and time Deadline occurs at.
     * @return Simplified date and time Deadline occurs at in String form.
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
     * Converts Deadline to String for Storage.
     * @return Deadline String for storage.
     */
    @Override
    public String toStorageString() {
        return ("D | " + this.getIsDoneBinary() + " | " + super.taskString + " | " + this.getDateTimeStorage());
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
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
