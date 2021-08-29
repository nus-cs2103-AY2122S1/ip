package duke.task;

/**
 * Represents the task which has deadline.
 *
 * @author QIN GUORUI
 */
public class Deadline extends Task {
    /** Stores the deadline. */
    protected String by;

    /**
     * Creates a deadline task.
     *
     * @param description The content of this task.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = dateAndTime(by);
    }

    /**
     * Returns whether the two times are equal.
     *
     * @param time The time's string representation.
     * @return A boolean.
     */
    @Override
    public boolean compareTime(String time) {
        return by.equals(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
