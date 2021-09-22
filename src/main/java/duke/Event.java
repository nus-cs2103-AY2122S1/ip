package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with an additional LocalDate field storing an event date.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * @param description Description of Event.
     * @param date date of event, formatted in YYYY-MM-DD.
     * @throws DukeException if description is empty.
     * @throws DateTimeParseException If date format is invalid.
     */
    public Event(String description, String date) throws DukeException, DateTimeParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public Event clone() {
        try {
            Event clone = new Event(description, date.toString());
            if (isDone) {
                clone.markAsDone();
            }
            return clone;
        } catch (DukeException e) {
            return null; // This Exception can never happen.
        }
    }

    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(), description, dateString);
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }
}
