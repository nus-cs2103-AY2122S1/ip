/**
 *  This class represents a Deadline
 *  A Deadline: tasks that need to be done before a specific date/time
 * @author Ryan Tian Jun
 */
public class DeadLine extends Task {

    private String by;

    public DeadLine(String description, TYPE type, String by) {
        super(description, type);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
