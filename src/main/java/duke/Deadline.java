package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with an additional date field storing a deadline.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * @param description Description of Task.
     * @param date Deadline for task, formatted in YYYY-MM-DD.
     * @throws DukeException if description is empty.
     * @throws DateTimeParseException If date format is invalid.
     */
    public Deadline(String description, String date) throws DukeException, DateTimeParseException {
        super(description);
        this.date = LocalDate.parse(date);
    }

    public Deadline clone() {
        try {
            Deadline clone = new Deadline(description, date.toString());
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
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(), description, dateString);
    }

    public String getDate() {
        return date.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }
}
