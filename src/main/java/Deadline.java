/**
 * A task that the user wants to complete by a certain point in time.
 */
public class Deadline extends Task{
    protected String deadline;

    /**
     * Constructor for Deadline class.
     * @param description Description for the task at hand.
     * @param deadline Time the task has to be completed by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Provides a String representation of the Deadline.
     * @return A String representation of the Deadline.
     */
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + "(by: " + this.deadline + ")";
    }
}
