/**
 * A type of task that tracks a deadline (date and time) and the task description.
 *
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the Deadline's string.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the task type of Todo.
     *
     * @return 1 to represent Deadline task type.
     */
    @Override
    public int taskType() {
        return 1;
    }

    /**
     * Returns the description in the format that will be saved into Hard drive.
     *
     * @return String
     */
    @Override
    public String savedFormat() {
        return super.description + "/~/" + this.by;
    }
}
