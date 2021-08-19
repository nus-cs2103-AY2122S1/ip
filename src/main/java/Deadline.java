/**
 * Deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline with the provided description and date/time.
     * @param description The description of the Deadline task.
     * @param by The date/time the Deadline needs to be done before.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline into a String that represents the Deadline.
     * @return The String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
