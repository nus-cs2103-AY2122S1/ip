package sun.data.task;

/**
 * Class that represents a Deadline task.
 *
 * @author Won Ye Ji
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param by Date the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }

    /**
     * Returns the string representation of the Deadline task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public String toSave() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
