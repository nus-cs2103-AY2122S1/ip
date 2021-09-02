package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of task that contains a due by deadline.
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * This constructor creates a Deadline type task object.
     *
     * @param description text description of Deadline task.
     * @param dateTime due date of Deadline task.
     */
    public Deadline(String description, Object dateTime) {
        super(description);

        if (dateTime instanceof LocalDateTime) {
            this.dateTime = (LocalDateTime) dateTime;
        } else {
            this.date = (LocalDate) dateTime;
        }
    }

    /**
     * This method formats task description and date in a user-friendly way,
     * additionally marking the Deadline task type with [D].
     *
     * @return String of formatted task description and deadline.
     */
    @Override
    public String toString() {

        if (this.dateTime != null) {
            return String.format("[D]%s (by: %s)", super.toString(),
                    this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")));
        } else {
            return String.format("[D]%s (by: %s)", super.toString(),
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        }
    }
}
