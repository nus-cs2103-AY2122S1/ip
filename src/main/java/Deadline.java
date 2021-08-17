/**
 * A Task of type Deadline
 */
public class Deadline extends Task{
    /**
     * When is the deadline due on?
     */
    protected String by;

    /**
     * The Constructor
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[S]" + super.toString() + " (by: " + by + ")";
    }
}
