package bribot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bribot.exception.DukeException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected LocalDateTime dateTime;

    /**
     * Creats an event with a description, time and date.
     * @param description given description.
     * @param dateString given date.
     * @param timeString given time.
     * @throws DukeException
     */
    public Event(String description, String dateString, String timeString) throws DukeException {
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
     * Returns the type of event to be written to the text file during saving.
     * @return the type of event.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Returns the description of the event.
     * @return the description of the event.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the date and time of the event.
     * @return the string representation of the date and time of the event.
     */
    @Override
    public String getDateTimeString() {
        return this.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns the string representation of the event.
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: "
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
