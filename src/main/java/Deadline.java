/**
 * Represents a deadline, which is a subtype of a Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * A constructor of a Deadline.
     *
     * @param description Description of the Deadline.
     * @param by The date and/or time by which the task should be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of a Deadline.
     *
     * @return [D], the description and the by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}