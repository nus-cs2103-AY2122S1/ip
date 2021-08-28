package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks which have a due date
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Public constructor which creates a new Deadline object with a due date.
     *
     * @param taskName The description of the Deadline
     * @param deadline The due date of the Deadline
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return The string representation of the due date.
     */
    public String getWhen() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dtf.format(deadline);
    }

    /**
     * Gets the string representation of the Deadline object.
     *
     * @return The string representation of the deadline object.
     */
    @Override
    public String displayInfo() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }

    /**
     * Gets the string representation of the Deadline object,
     * used for writing into the local file system.
     *
     * @return The string representation of the deadline object,
     *          used for writing into the local file system.
     */
    @Override
    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("D | 1 | %s | %s", this.getTaskName(), this.getWhen());
        } else {
            return String.format("D | 0 | %s | %s", this.getTaskName(), this.getWhen());
        }
    }
}
