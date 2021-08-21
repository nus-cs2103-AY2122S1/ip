package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with an additional dateTime field storing a deadline
 */
public class Deadline extends Task {
    private LocalDate dateTime;

    /**
     * @param description Description of Task
     * @param dateTime Deadline for task, formatted in YYYY-MM-DD
     * @throws DukeException
     */
    public Deadline(String description, String dateTime) throws DukeException, DateTimeParseException {
        super(description);
        this.dateTime = LocalDate.parse(dateTime);
    }
    public String toString() {
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(), description, dateString);
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }
}
