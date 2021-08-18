/**
 * A task of type deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * constructor of the class
     * @param description description of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of event
     * @return String representation of event
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}