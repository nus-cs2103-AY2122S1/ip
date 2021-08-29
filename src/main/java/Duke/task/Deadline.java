package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task. A deadline task has to be completed by a certain date and time.
 */
public class Deadline extends Task{

    private String deadlineStr;
    private LocalDateTime deadline;

    public Deadline(String details, String deadline) {
        super(details);
        try {
            this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            deadlineStr = null;
        } catch (DateTimeParseException e) {
            deadlineStr = deadline;
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
     * Most likely due to incorrect input format.
     *
     * @return Task deadline as a String.
     */
    public String getDeadlineStr() {
        return deadlineStr;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (deadline == null) {
            return "[D]" + super.toString() + " (by: " + deadlineStr + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
                    + ")";
        }
    }
}
