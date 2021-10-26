package duke.tasks;

import static duke.common.Formats.DT_DATA_FORMAT;
import static duke.common.Formats.DT_OUTPUT_FORMAT;

import java.time.LocalDateTime;

/**
 * Represents a task a user has to complete by a certain date/time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Default constructor for a Deadline task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline.format(DT_OUTPUT_FORMAT));
    }

    @Override
    public String getData() {
        return String.format("D | %s | %s", super.getData(), deadline.format(DT_DATA_FORMAT));
    }
}
