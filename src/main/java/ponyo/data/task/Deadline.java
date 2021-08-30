package ponyo.data.task;

/**
 * A Deadline task object that has a description and a by-date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a new deadline instance.
     *
     * @param description a string description of the deadline
     * @param by a string by-date for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // File operations
    @Override
    public String toStringInFile() {
        return "D - " + super.toStringInFile() + " - " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
