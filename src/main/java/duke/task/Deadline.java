package duke.task;

/**
 * Represents the task which has deadline.
 *
 * @author QIN GUORUI
 */
public class Deadline extends Task {
    /** Stores the deadline. */
    private String formatBy;

    /**
     * Creates a deadline task.
     *
     * @param description The content of this task.
     * @param formatBy The deadline(in format).
     */
    public Deadline(String description, String formatBy) {
        super(description, formatBy);
        this.formatBy = formatBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy + ")";
    }
}
