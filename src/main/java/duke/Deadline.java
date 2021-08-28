package duke;

/**
 * Represents a task with deadline.
 *
 * @author Adam Ho
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a task with the specified deadline.
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Represents deadline task as a string.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
