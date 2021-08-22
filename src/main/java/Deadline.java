/**
 * Deadline class represents tasks that need to be done before a
 * specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Chng Zi Hao
 */
public class Deadline extends Task{
    private String by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the deadline.
     * @param by The due date of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
