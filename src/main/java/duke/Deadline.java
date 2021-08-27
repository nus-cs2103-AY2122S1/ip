package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a specific deadline to be completed by.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructor for deadline task.
     *
     * @param description The string description of deadline.
     * @param deadline The LocalDate of a specific day.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    private String formatDate() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * String representation of deadline task.
     *
     * @return String form of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + formatDate() + ")";
    }
}
