package duke;

import java.time.LocalDate;

/**
 * Class for deadline tasks.
 */
public class Deadline extends Task {
    /**
     * Constructs a deadline task.
     *
     * @param description String describing the task.
     * @param time Deadline for the task.
     * @throws DukeException Thrown if time could not be parsed.
     */
    public Deadline(String description, LocalDate time) throws DukeException {
        super(description, time);
        category = TaskType.deadline;
        assert description != null : "description should not be null";
    }

    /**
     * Returns string representation of a deadline task.
     *
     * @return String describing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTimeString() + ")";
    }
}
