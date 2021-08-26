package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline, a task with a deadline.
 */
public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Gives the type of task.
     *
     * @return D for Deadline
     */
    @Override
    public String type() {
        return "D";
    }

    /**
     * Gives save-friendly information.
     *
     * @return save-friendly information.
     */
    @Override
    public String getSaveInfo() {
        return super.getSaveInfo() + "| "
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
