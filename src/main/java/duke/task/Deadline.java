package duke.task;

/**
 * Represents the task which has deadline.
 *
 * @author QIN GUORUI
 */
public class Deadline extends Task {
    /** Stores the deadline. */
    private String by;

    /**
     * Creates a deadline task.
     *
     * @param description The content of this task.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description, by);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
