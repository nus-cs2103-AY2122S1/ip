package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */

public class Deadline extends Task{
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates a deadline object that has a description, date and time.
     * @param description given description.
     * @param dateString given date in a formatted string.
     * @param timeString given time in a formatted string.
     * @throws DukeException if the dateString or timeString is of the wrong format.
     */
    public Deadline(String description, String dateString, String timeString) throws DukeException {
        super(description);
        try {
            this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops! Make sure that your date and time is valid" +
                    " and is formatted as 'dd/MM/yyyy HHmm'.");
        }
    }

    /**
     * Returns the type of task that will be written into the file upon saving tasks.
     * @return String of the deadline type.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the description of the deadline.
     * @return the description of the deadline in a String.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date and time in a string.
     * @return the date and time in a string.
     */
    @Override
    public String getDateTimeString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns the string form of the deadline.
     * @return the string form of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("HHmm"))+ ")";
    }
}
