package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task. A deadline task has to be completed by a certain date and time.
 */
public class Deadline extends Task {

    private static final char LABEL = 'D';
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private String deadlineWrongFormat; // Used to store incorrect deadline formats.
    private LocalDateTime deadline;

    /**
     * Constructor.
     *
     * @param details Details of the deadline task.
     * @param deadline Deadline of the deadline task.
     */
    public Deadline(String details, String deadline) {
        super(LABEL, details);
        try {
            this.deadline = LocalDateTime.parse(deadline, FORMATTER);
            deadlineWrongFormat = null;
        } catch (DateTimeParseException e) {
            deadlineWrongFormat = deadline;
            this.deadline = null;
        }
    }

    /**
     * Returns deadline of task.
     *
     * @return Task deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns date of task.
     *
     * @return Task deadline.
     */
    @Override
    public LocalDateTime getDate() {
        return getDeadline();
    }

    /**
     * Returns deadline of task as a String.
     *
     * @return Task deadline as a String.
     */
    public String getDeadlineAsStr() {
        if (deadline == null) {
            return deadlineWrongFormat;
        } else {
            return deadline.format(FORMATTER);
        }
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString() + " (by: " + getDeadlineAsStr() + ")";
    }
}
