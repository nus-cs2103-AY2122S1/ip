package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A {@code Deadline} class that extends from {@code Task}
 * and sets a LocalDateTime {@code deadline}.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Initialise constructor for a deadline.
     *
     * @param taskName Description of the task.
     * @param deadline Deadline of the task, indicated with /by {time}
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Returns a {@code string} representation of a deadline.
     * @return Deadline name and whether it is completed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

}
