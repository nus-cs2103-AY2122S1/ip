package bribot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bribot.exception.DukeException;

/**
 * Represents a deadline task.
 */

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

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
            this.dateTime = date.atTime(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Oops! Make sure that your date and time is valid"
                    + " and is formatted as 'dd/MM/yyyy HHmm'.");
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
        return "[D]" + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + time.format(DateTimeFormatter.ofPattern("HHmm")) + ")";
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Todo) {
            return 1;
        } else if (o instanceof Deadline) {
            return this.dateTime.compareTo(((Deadline) o).dateTime);
        } else if (o instanceof Event) {
            return this.dateTime.compareTo(((Event) o).dateTime);
        }
        return 0;
    }
}
