package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.error.DukeException;

/**
 * Represent a deadline task which stores a date for the deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDate date;

    /**
     * Constructs a DeadlineTask object.
     *
     * @param description Description of the task.
     * @param date Deadline date in the format yyyy-mm-dd.
     * @throws DukeException If the date format is wrong.
     */
    public DeadlineTask(String description, String date) throws DukeException {
        super(description);
        this.type = "D";
        this.date = parseDateString(date);

    }

    /**
     * Constructs a DeadlineTask object.
     *
     * @param description Description of the task.
     * @param isDone The status of the task.
     * @param date Deadline date.
     */
    public DeadlineTask(String description, boolean isDone, String date) {
        super(description, isDone);
        this.type = "D";
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns a String representation of the deadline date.
     *
     * @return Deadline date.
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Change the date.
     *
     * @param newDate New date.
     * @throws DukeException If the date format is wrong.
     */
    public void setDate(String newDate) throws DukeException{
        date = parseDateString(newDate);
    }

    /**
     * Parses the date string into a LocalDate object.
     *
     * @param date String format of the date.
     * @return The LocalDate object.
     * @throws DukeException
     */
    public LocalDate parseDateString(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! wrong date format.\nthe format should be:\n\tyyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
