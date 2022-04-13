package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a deadline, a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline, which takes in a task name and a deadline.
     *
     * @param taskName name of task.
     * @param deadline deadline of the task.
     */
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
    public String getType() {
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

    /**
     * Overriden toString method.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString(this.deadline, "by ");
    }
}
