package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Deadline (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh
 */
public class Deadline extends Task {

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param date Date of deadline.
     */
    public Deadline(String taskstr, LocalDate date) {
        super(taskstr);
        super.date = date;
        super.time = null;
    }

    /**
     * Constructor to initialize Deadline. With time.
     * @param taskstr Task.
     * @param deadline Deadline of task.
     * @param time Time of task.
     */
    public Deadline(String taskstr, LocalDate deadline, LocalTime time) {
        super(taskstr);
        super.date = deadline;
        super.time = time;
    }

    /**
     * Returns date Deadline occurs at, in String form.
     * @return
     */
    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns time Deadline occurs at.
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
     * Returns simplified date and time Deadline occurs at, in String form.
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
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        Parser parser = new Parser();
        if (this.time == null) {
            return "[D] " + super.toString() + "(by: "
                    + parser.simplifyDate(this.date) + ")";
        } else {
            return "[D] " + super.toString() + "(by: "
                    + parser.simplifyDate(this.date)
                    + " " + parser.simplifyTime(this.time)
                    + ")";
        }
    }

}
