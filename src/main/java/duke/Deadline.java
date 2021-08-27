package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     * @param taskName Name of task.
     * @param deadline Deadline of task.
     * @throws DateTimeParseException If deadline cannot be parsed.
     */
    public Deadline(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = DukeDateFormatter.stringToDateTime(deadline.trim());
    }

    /**
     * Constructor for Deadline class specifying if a Deadline is done.
     * @param taskName Name of task.
     * @param isDone True implies task has been done.
     * @param deadline Deadline of task.
     * @throws DateTimeParseException If the deadline input string cannot be parsed.
     */
    public Deadline(String taskName, boolean isDone, String deadline) throws DateTimeParseException {
        super(taskName, isDone);
        this.deadline = DukeDateFormatter.stringToDateTime(deadline.trim());
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String getFileString() {
        return String.format("D | %s | %s", super.getFileString(), DukeDateFormatter.dateTimeToFile(this.deadline));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DukeDateFormatter.dateTimeToString(this.deadline));
    }
}
