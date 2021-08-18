/**
 * This class represents the Deadline event.
 * @author Nigel Tan
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor
     * @param description the name of the task
     * @param pos the position in the list
     * @param by the deadline
     */
    public Deadline(String description, int pos, String by) {
        super(description, pos);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
