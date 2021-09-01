package duke;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDate;

    /**
     * Constructs a Deadline task object.
     *
     * @param description Task description.
     * @param isCompleted Completion status of task.
     * @param deadlineDate Deadline of task.
     */
    public Deadline(String description, Boolean isCompleted, LocalDateTime deadlineDate) {
        super(description, isCompleted);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String typeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineDate + ")";
    }
}
