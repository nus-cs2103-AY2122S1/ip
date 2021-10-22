package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a deadline task.
 * Deadline tasks are tasks that needs to be done by a specific date/time.
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * Constructor for Deadline.
     *
     * @param description description of deadline task
     * @param dateTime date and time deadline task is due
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
        assert !description.equals("") : "Deadline task description cannot be empty";
    }

    /**
     * Gets string representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(dateTimeFormatter) + ")";
    }
}
