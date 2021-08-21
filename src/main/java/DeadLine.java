/**
 *  This class represents a Deadline.
 *  A Deadline: tasks that need to be done before a specific date/time.
 *
 * @author Ryan Tian Jun.
 */
public class DeadLine extends Task {

    private String by;

    public DeadLine(String description, TYPE type, String by) {
        super(description, type);
        this.by = by;
    }

    public DeadLine(TYPE type, boolean isDone, String description, String by) {
        super(type, isDone, description);
        this.by = by;
    }

    @Override
    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
