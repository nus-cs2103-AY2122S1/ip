package duke;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline task with a deadline.
 */
public class Deadline extends Task{
    private LocalDateTime deadlineDate;

    public Deadline(String description, Boolean completionStatus, LocalDateTime deadlineDate) {
        super(description, completionStatus);
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
