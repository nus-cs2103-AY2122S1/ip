/**
 * This class encapsulates a Deadline task.
 *
 * @author Kleon Ang
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description A string describing the Deadline task.
     * @param by A string indicating when the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string including the Deadline's icon, description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
