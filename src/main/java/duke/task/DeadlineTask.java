package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with due time
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor of <code>DeadlineTask</code>
     *
     * @param content content of the task
     * @param isDone true if the task is done, otherwise false
     * @param deadline the due time of the deadline
     */
    public DeadlineTask(String content, boolean isDone, LocalDateTime deadline) {
        super(content, isDone);
        this.deadline = deadline;
    }

    /**
     * Getter for deadline due time
     *
     * @return the due date
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns the string representation of the object
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }
}
