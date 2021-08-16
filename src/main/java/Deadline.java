/**
 * Represents a specific task (an event) containing the description
 * and the deadline of the task that the user wants
 * to add in his or her todo list.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
