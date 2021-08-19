/**
 * This class represents the Deadline event.
 * @author Nigel Tan
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor
     * @param description the name of the task
     * @param by the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
