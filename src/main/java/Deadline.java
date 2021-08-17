/**
 * Class that encapsulates a Deadline task.
 */
public class Deadline extends Task {
    /**
     * String that indicates the deadline for the task.
     */
    protected String deadline;

    /**
     * Public constructor to create a Deadline task
     *
     * @param description Description of the deadline task.
     * @param deadline Deadline to complete task by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of a deadline task.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline +")";
    }

}
