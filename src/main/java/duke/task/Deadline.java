package duke.task;

/**
 * Represents the task which has deadline.
 *
 * @author QIN GUORUI
 */
public class Deadline extends Task {
    /** Stores the deadline. */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = dateAndTime(by);
    }

    @Override
    public boolean compareTime(String time) {
        return by.equals(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
