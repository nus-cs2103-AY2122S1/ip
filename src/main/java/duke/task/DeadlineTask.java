package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.error.DukeException;

/**
 * Represent a deadline task which stores a date for the deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDate time;

    /**
     * Constructs a DeadlineTask object.
     *
     * @param description Description of the task.
     * @param time Deadline date in the format yyyy-mm-dd.
     * @throws DukeException If the date format is wrong.
     */
    public DeadlineTask(String description, String time) throws DukeException {
        super(description);
        this.type = "D";
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! wrong date format.\nthe format should be:\n\tyyyy-mm-dd");
        }
    }

    /**
     * Constructs a DeadlineTask object.
     *
     * @param description Description of the task.
     * @param isDone The status of the task.
     * @param time Deadline date.
     */
    public DeadlineTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.type = "D";
        this.time = LocalDate.parse(time);
    }

    /**
     * Returns a String representation of the deadline date.
     *
     * @return Deadline date.
     */
    public String getTime() {
        return time.toString();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
